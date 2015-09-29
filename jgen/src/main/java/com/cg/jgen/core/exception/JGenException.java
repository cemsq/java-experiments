package com.cg.jgen.core.exception;

/**
 *
 */
public class JGenException extends RuntimeException {

    public JGenException(String message) {
        super(message);
    }

    public JGenException(String message, Throwable cause) {
        super(message, cause);
    }

    public JGenException(Throwable cause) {
        super(cause);
    }
}
