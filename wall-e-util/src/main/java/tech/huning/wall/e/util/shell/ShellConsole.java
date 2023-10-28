package tech.huning.wall.e.util.shell;

import tech.huning.wall.e.util.shell.exception.ShellException;
import tech.huning.wall.e.util.shell.model.ShellCommand;
import tech.huning.wall.e.util.shell.model.ShellResult;
import tech.huning.wall.e.util.shell.specs.IShellConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShellConsole implements IShellConsole {

    private static final Logger logger = LoggerFactory.getLogger(ShellConsole.class);

    private static final ShellConsole instance = new ShellConsole();

    private ShellConsole(){}

    public static ShellConsole getInstance() {
        return instance;
    }

    @Override
    public ShellResult exec(ShellCommand cmd) throws ShellException {

        ShellResult shellResult = new ShellResult();
        Process process = null;
        try {
            logger.debug("execute {} start", cmd.getLine());

            List<String> cmdJoiner = new ArrayList<>();
            cmdJoiner.add("sh");
            cmdJoiner.add("-c");
            cmdJoiner.add(cmd.getLine());
            ProcessBuilder pb =new ProcessBuilder(cmdJoiner);
            process = pb.start();

            logger.debug("execute {} waitFor", cmd.getLine());
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                throw new ShellException(e);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorBuffer = new StringBuilder();
            String error;
            while ((error = errorReader.readLine()) != null) {
                errorBuffer.append(error);
            }

            if(errorBuffer.length() > 0) {
                shellResult.setSuccess(false);
                shellResult.setError(errorBuffer.toString());
                logger.error("execute {}, error {}", cmd.getLine(), errorBuffer.toString());
                return shellResult;
            }

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder inputBuffer = new StringBuilder();
            String input;
            while ((input = inputReader.readLine()) != null) {
                inputBuffer.append(input);
            }

            if(inputBuffer.length() > 0) {
                shellResult.setData(inputBuffer.toString());
                logger.debug("execute {}, data {}", cmd.getLine(), inputBuffer.toString());
            }

            shellResult.setSuccess(true);
        } catch (IOException e) {
            throw new ShellException(e);
        } finally {
            if(null != process) {
                process.destroy();
            }
        }

        logger.debug("execute {} finish, result {}", cmd.getLine(), shellResult.getData());
        return shellResult;
    }

}
