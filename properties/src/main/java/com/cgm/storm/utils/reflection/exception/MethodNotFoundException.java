package com.cgm.storm.utils.reflection.exception;

import com.cgm.storm.utils.reflection.Reflections;

/**
 *
 */
public class MethodNotFoundException extends ReflectionsException {

    public MethodNotFoundException(Class clazz, String methodName) {
        super(message(clazz, methodName));
    }

    public MethodNotFoundException(Class clazz, String field, Reflections.MethodType type) {
        super(message(clazz, field, type));
    }

    private static String message(Class clazz, String methodName) {
        return "'" + methodName + "' "+ " Method not found. Class: " + clazz.getName();
    }

    private static String message(Class clazz, String field, Reflections.MethodType type) {
        return message(clazz, type.name()) + ". Field: " + field;
    }
}
