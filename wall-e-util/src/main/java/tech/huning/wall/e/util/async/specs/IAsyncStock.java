package tech.huning.wall.e.util.async.specs;

import java.util.List;

/**
 * 异步任务池
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface IAsyncStock {

    int getTotal();
    List<IAsyncTask> getList();

}
