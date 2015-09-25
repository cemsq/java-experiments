package com.cgm.storm.utils.properties.adapter.annotation;

/**
 * 
 */
public @interface Item {
    public String field();

    public String name();

    public String desc();

    public String fieldOwner() default "";

    public Class clazz() default Uninitialized.class;

    /**
     *
     */
    static final class Uninitialized {
    }
}
