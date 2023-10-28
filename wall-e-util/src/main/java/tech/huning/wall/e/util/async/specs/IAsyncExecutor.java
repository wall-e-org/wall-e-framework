package tech.huning.wall.e.util.async.specs;

import tech.huning.wall.e.util.async.exception.AsyncException;

/**
 * 异步处理执行器
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface IAsyncExecutor<T> {

    default void run(IAsyncTask<T> task) throws InterruptedException {
        try{
            exec(task);
            succ(task);
        }catch (AsyncException e) {
            fail(task, e);
        }catch (InterruptedException e) {
            throw e;
        } catch (Exception e) {
            fail(task, new AsyncException(e));
        }
    }

    void exec(IAsyncTask<T> task) throws AsyncException, InterruptedException;

    default void fail(IAsyncTask<T> task, AsyncException exception) {

    }

    default void succ(IAsyncTask<T> task) {

    }

}
