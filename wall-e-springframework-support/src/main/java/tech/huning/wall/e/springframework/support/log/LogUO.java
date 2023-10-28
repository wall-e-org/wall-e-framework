package tech.huning.wall.e.springframework.support.log;

import org.springframework.util.StopWatch;

import java.util.List;

/**
 * 日志数据模型
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class LogUO {

    private final StopWatch stopWatch = new StopWatch();
    private String id;
    private String url;
    private String className;
    private String methodName;
    private List<Object> params;
    private Object result;
    private Throwable error;
    private long costTimeMillis;

    public LogUO(){
        this.stopWatch.start();
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public long getCostTimeMillis() {
        return costTimeMillis;
    }

    public void setCostTimeMillis() {
        this.stopWatch.stop();
        this.costTimeMillis = this.stopWatch.getTotalTimeMillis();
    }

}
