package tech.huning.wall.e.springframework.support.log;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志配置信息
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class LogConfig {

    public static final String PREFIX = "aurora.log";

    private boolean enabled;                               // 是否启用
    private boolean printParams;                           // 是否打印入参
    private boolean printResult;                           // 是否打印结果
    private boolean printError;                            // 是否打印错误
    private Exclude excludeParams = new Exclude();         // 入参打印例外配置
    private Exclude excludeResult = new Exclude();         // 结果打印例外配置

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPrintParams() {
        return printParams;
    }

    public void setPrintParams(boolean printParams) {
        this.printParams = printParams;
    }

    public boolean isPrintResult() {
        return printResult;
    }

    public void setPrintResult(boolean printResult) {
        this.printResult = printResult;
    }

    public boolean isPrintError() {
        return printError;
    }

    public void setPrintError(boolean printError) {
        this.printError = printError;
    }

    public Exclude getExcludeParams() {
        return excludeParams;
    }

    public void setExcludeParams(Exclude excludeParams) {
        this.excludeParams = excludeParams;
    }

    public Exclude getExcludeResult() {
        return excludeResult;
    }

    public void setExcludeResult(Exclude excludeResult) {
        this.excludeResult = excludeResult;
    }

    /**
     * 例外信息
     */
    public static class Exclude {

        private List<String> classNames = new ArrayList<>();
        private List<String> methodNames = new ArrayList<>();

        public List<String> getClassNames() {
            return classNames;
        }

        public void setClassNames(List<String> classNames) {
            this.classNames = classNames;
        }

        public List<String> getMethodNames() {
            return methodNames;
        }

        public void setMethodNames(List<String> methodNames) {
            this.methodNames = methodNames;
        }

    }

}
