package com.cgm.storm.utils.common;

import com.cgm.storm.utils.common.exception.BatchExecutorException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 */
public class BatchExecutor {
    private static final Logger log = LoggerFactory.getLogger(BatchExecutor.class);

    private BatchExecutor() {
    }

    public static String exec(String cmd, boolean suppressOutput) {
        StreamGobbler sg = new StreamGobbler();
        sg.setSuppressOutput(suppressOutput);
        return exec(cmd, sg);
    }

    public static String exec(String cmd, StreamGobbler sg) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(cmd), "cmd command must be not null or not empty");
        log.info("Executing command: {}", cmd);

        try {
            Process proc = Runtime.getRuntime().exec(cmd);

            // any output?
            sg.setInputStream(proc.getInputStream());

            // kick off
            sg.start();

            // any error???
            int exitVal = proc.waitFor();

            if (exitVal != 0) {
                throw new BatchExecutorException("Unable to run command: " + cmd);
            }
        } catch (IOException | InterruptedException e) {
            throw new BatchExecutorException("Unable to run command: " + cmd, e);
        }

        return sg.getText();
    }
}
