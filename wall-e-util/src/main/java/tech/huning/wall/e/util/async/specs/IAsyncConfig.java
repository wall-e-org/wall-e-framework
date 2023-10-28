package tech.huning.wall.e.util.async.specs;

/**
 * 异步处理配置类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public interface IAsyncConfig {

    IAsyncTopic getTopic();

    default int getTaskCapacity() {
        return 200;
    }

    default int getExecutorCapacity() {
        return 1;
    }

    default int getConcurrent() {
        return 2;
    }

    default String getThreadPrefix(){
        return "async-scheduler-exec-";
    }

    default int getThreadStartId(){
        return 0;
    }

}
