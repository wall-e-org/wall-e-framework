package tech.huning.wall.e.util.async.specs;

/**
 * 异步任务
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface IAsyncTask<T> {

    String getId();

    T getData();

}
