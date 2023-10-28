package tech.huning.wall.e.util.shell.model;

/**
 * Shell指令
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class ShellCommand {

    private String line;

    public ShellCommand(){}

    public ShellCommand(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

}
