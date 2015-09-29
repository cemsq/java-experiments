package com.cg.jgen.cmd.exception;

/**
 *
 */
public class CommandLineException extends RuntimeException {
    public CommandLineException() {
        super();
    }

    public CommandLineException(String message) {
        super(message);
    }

    public CommandLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandLineException(Throwable cause) {
        super(cause);
    }
}
