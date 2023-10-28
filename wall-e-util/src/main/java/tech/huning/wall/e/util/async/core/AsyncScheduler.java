package tech.huning.wall.e.util.async.core;

import tech.huning.wall.e.util.async.exception.AsyncException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.huning.aurora.util.async.specs.*;
import tech.huning.wall.e.util.async.specs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 异步任务调度器
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public final class AsyncScheduler implements IAsyncScheduler {

    private static final Logger logger = LoggerFactory.getLogger(AsyncScheduler.class);

    private static volatile AsyncScheduler instance;

    private final ConcurrentHashMap<String, IAsyncQueue> schedulers = new ConcurrentHashMap<String, IAsyncQueue>();

    private AsyncScheduler(){}

    public static AsyncScheduler getInstance() {
        if(instance == null) {
            synchronized (AsyncScheduler.class) {
                if(instance == null) {
                    instance = new AsyncScheduler();
                }
            }
        }
        return instance;
    }

    public IAsyncQueue getClient(IAsyncTopic topic) throws AsyncException {
        IAsyncQueue queue =  schedulers.get(topic.getCode());
        if(null == queue) {
            throw new AsyncException(AsyncFault.SERVER_NOT_LISTEN);
        }
        return queue;
    }

    @Override
    public IAsyncQueue getServer(IAsyncTopic topic) throws AsyncException {
        return getClient(topic);
    }

    public IAsyncQueue getServer(IAsyncConfig config) throws AsyncException {

        if(null == config) {
            throw new AsyncException(AsyncFault.CONFIG_IS_NULL);
        }

        if(null == config.getTopic()) {
            throw new AsyncException(AsyncFault.TOPIC_IS_NULL);
        }

        IAsyncQueue queue = schedulers.get(config.getTopic().getCode());
        if(null != queue) {
            return queue;
        }

        synchronized (config.getTopic().getCode()) {
            queue = new IAsyncQueue() {

                private LinkedBlockingQueue<IAsyncTask> tasks = new LinkedBlockingQueue<IAsyncTask>(config.getTaskCapacity());
                private ConcurrentHashMap<String,IAsyncTask> handlingTasks = new ConcurrentHashMap<String,IAsyncTask>();
                private List<IAsyncExecutor> executors = new ArrayList<IAsyncExecutor>();
                private Lock lock = new ReentrantLock();
                private ExecutorService executorService = Executors.newFixedThreadPool(config.getConcurrent(), AsyncThreadFactory.build(config));
                private volatile boolean listening = false;
                private IAsyncOverflowHandler handler;

                @Override
                public IAsyncQueue schedule(IAsyncTask task) throws AsyncException {
                    if(tasks.offer(task)){
                        return this;
                    }
                    if(null != this.handler) {
                        this.handler.handle(task);
                    }else{
                        throw new AsyncException(AsyncFault.TASK_QUEUE_IS_FULL);
                    }
                    return this;
                }

                @Override
                public IAsyncQueue register(IAsyncExecutor executor) throws AsyncException {
                    lock.lock();
                    try{
                        if(executors.size() >= config.getExecutorCapacity()) {
                            throw new AsyncException(AsyncFault.EXECUTOR_IS_FULL);
                        }
                        executors.add(executor);
                    }finally {
                        lock.unlock();
                    }
                    return this;
                }

                @Override
                public IAsyncQueue register(IAsyncOverflowHandler handler) throws AsyncException {
                    this.handler = handler;
                    return this;
                }

                @Override
                public IAsyncStock getStock() throws AsyncException {

                    try{
                        IAsyncTask[] array = new IAsyncTask[tasks.size()];
                        tasks.toArray(array);
                        return new IAsyncStock() {
                            @Override
                            public int getTotal() {
                                return array.length;
                            }

                            @Override
                            public List<IAsyncTask> getList() {
                                return Arrays.asList(array);
                            }
                        };
                    }catch (Exception e) {
                        throw new AsyncException(e);
                    }

                }

                @Override
                public IAsyncHandling getHandling() throws AsyncException {

                    try{
                        IAsyncTask[] array = new IAsyncTask[handlingTasks.size()];
                        handlingTasks.values().toArray(array);
                        return new IAsyncHandling() {
                            @Override
                            public int getTotal() {
                                return array.length;
                            }

                            @Override
                            public List<IAsyncTask> getList() {
                                return Arrays.asList(array);
                            }
                        };
                    }catch (Exception e) {
                        throw new AsyncException(e);
                    }

                }

                @Override
                public void listen() throws AsyncException {

                    if(this.listening) {
                        logger.warn("scheduler {} is already listening.", config.getTopic().getCode());
                        return;
                    }

                    for(int i = 0; i < config.getConcurrent(); i++) {

                        executorService.execute(() -> {
                            while (true) {
                                IAsyncTask task = null;
                                try {
                                    logger.debug("take start");
                                    task = tasks.take();
                                    logger.debug("take finish");
                                    handlingTasks.put(task.getId(), task);
                                    for(IAsyncExecutor executor : executors) {
                                        executor.run(task);
                                    }
                                } catch (InterruptedException e) {
                                    logger.debug("scheduler " + config.getTopic().getCode() + " is interrupted.", e);
                                    break;
                                } finally {
                                    if(null != task) {
                                        handlingTasks.remove(task.getId());
                                    }
                                }
                            }
                        });

                    }

                    this.listening = true;
                }

                @Override
                public void shutdown() {
                    logger.debug("scheduler {} is shutdown now.", config.getTopic().getCode());
                    IAsyncQueue queue = schedulers.get(config.getTopic().getCode());
                    if(null == queue) {
                        return;
                    }
                    schedulers.remove(config.getTopic().getCode());
                    executorService.shutdownNow();
                }

            };

            schedulers.put(config.getTopic().getCode(), queue);
        }

        return queue;
    }

}
