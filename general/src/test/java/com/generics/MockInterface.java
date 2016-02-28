package com.generics;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MockInterface {
    private static final Logger log = LoggerFactory.getLogger(MockInterface.class);

    public <T> T unique(Class<T> complex) {
        try {
            log.info("unique with parameter");

            return complex.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public void voidWithoutArguments() {

    }

    public Object objectWith2Arguments(int a, int b) {
        return new Object();
    }

    public <T> List<T> list(Class<T> complex) {
        log.info("list with parameter");
        return Lists.newArrayList();
    }

    public  Object unique() {

            log.info("unique - ");

        return new Object();
    }

    public <T> List<Object> list() {
        log.info("list -");
        return Lists.newArrayList();
    }
}
