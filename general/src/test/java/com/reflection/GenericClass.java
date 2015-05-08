package com.reflection;

/**
 *
 */
public class GenericClass<T> {
    private Class clazz;

    public GenericClass() {

    }

    public void add(T e) {
        System.out.println(e.getClass());
        System.out.println("value: " + e);
    }
}
