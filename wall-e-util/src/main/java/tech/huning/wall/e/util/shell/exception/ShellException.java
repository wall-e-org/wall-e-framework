package tech.huning.wall.e.util.shell.exception;

/**
 * Shell执行异常
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class ShellException extends Exception {

    public ShellException() {
        super();
    }

    public ShellException(String message) {
        super(message);
    }

    public ShellException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShellException(Throwable cause) {
        super(cause);
    }

}
