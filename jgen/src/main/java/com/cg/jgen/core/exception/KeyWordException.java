package com.cg.jgen.core.exception;

/**
 *
 */
public class KeyWordException extends RuntimeException {

    public KeyWordException(String message) {
        super(message);
    }

    public KeyWordException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyWordException(Throwable cause) {
        super(cause);
    }
}
