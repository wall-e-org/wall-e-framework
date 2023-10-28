package tech.huning.wall.e.util.async;

import com.alibaba.fastjson.JSON;
import tech.huning.wall.e.util.async.core.AsyncScheduler;
import tech.huning.wall.e.util.async.dto.VideoDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.huning.wall.e.util.async.specs.*;

import java.util.UUID;

/**
 * 异步处理器测试
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class AsyncSchedulerTest {

    private static final Logger logger = LoggerFactory.getLogger(AsyncSchedulerTest.class);
    private static final IAsyncTopic TOPIC_VIDEO_TRANSFER = () -> "TOPIC_VIDEO_TRANSFER";

    @Test
    public void test() {
        try {
            startServer();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            printStocks();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            printHandling();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            startClient();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void startServer() throws Exception {
        AsyncScheduler.getInstance().getServer(new IAsyncConfig() {
            @Override
            public int getTaskCapacity() {
                return 10;
            }
            public IAsyncTopic getTopic() {
                return TOPIC_VIDEO_TRANSFER;
            }

        }).register((IAsyncExecutor<VideoDTO>) task -> {
            logger.debug("start process>>>{}-{}", task.getData().getDuration(), task.getData().getTitle());
            logger.debug("end process<<<{}-{}", task.getData().getDuration(), task.getData().getTitle());
        }).register((IAsyncOverflowHandler) task -> logger.debug("overflow:{}", JSON.toJSONString(task)))
          .listen();
    }

    private void stopServer() throws Exception {
        AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER).shutdown();
    }

    private void startClient() throws Exception {
        for(long i = 0; i < 15; i++) {
            final long num = i;
            AsyncScheduler.getInstance().getClient(TOPIC_VIDEO_TRANSFER).schedule(new IAsyncTask<VideoDTO>() {
                private String id = UUID.randomUUID().toString();
                @Override
                public String getId() {
                    return id;
                }
                public VideoDTO getData() {
                    return new VideoDTO("VIDEO-" + num, num);
                }
            });
        }
    }

    private void printStocks() throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        IAsyncStock stocks = AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER).getStock();
                        logger.debug("STOCKS:{}|{}", stocks.getTotal(), JSON.toJSONString(stocks));
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void printHandling() throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        IAsyncHandling handling = AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER).getHandling();
                        logger.debug("HANDLING:{}|{}", handling.getTotal(), JSON.toJSONString(handling));
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
