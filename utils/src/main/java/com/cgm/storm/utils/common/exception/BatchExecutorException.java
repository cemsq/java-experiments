package com.cgm.storm.utils.common.exception;

/**
 *
 */
public class BatchExecutorException extends RuntimeException {

    public BatchExecutorException(String message) {
        super(message);
    }

    public BatchExecutorException(String message, Throwable cause) {
        super(message, cause);
    }
}
