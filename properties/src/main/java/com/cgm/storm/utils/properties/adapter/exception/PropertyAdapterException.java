package com.cgm.storm.utils.properties.adapter.exception;

/**
 * Created by César Mora on 20.10.2014.
 */
public class PropertyAdapterException extends RuntimeException {
    public PropertyAdapterException() {
    }

    public PropertyAdapterException(String message) {
        super(message);
    }

    public PropertyAdapterException(String message, Throwable cause) {
        super(message, cause);
    }
}
