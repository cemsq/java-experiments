package com.jgen.exception;

/**
 *
 */
public class JgenException extends RuntimeException {

    public JgenException(String message) {
        super(message);
    }

    public JgenException(String message, Throwable cause) {
        super(message, cause);
    }
}
