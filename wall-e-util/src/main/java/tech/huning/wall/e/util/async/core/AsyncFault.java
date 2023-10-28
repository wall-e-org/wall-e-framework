package tech.huning.wall.e.util.async.core;

import tech.huning.wall.e.util.async.specs.IAsyncFault;

/**
 * 异步处理错误
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public enum AsyncFault implements IAsyncFault {

    SERVER_NOT_LISTEN("服务未启动!"),
    TASK_QUEUE_IS_FULL( "任务队列已满!"),
    EXECUTOR_IS_FULL("执行器注册已满!"),
    CONFIG_IS_NULL("配置信息不能为空!"),
    TOPIC_IS_NULL("主题信息不能为空!"),
    SERVICE_EXCEPTION( "服务未知异常!");

    private String message;

    AsyncFault(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
