package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class DoubleTest {

    @Test
    public void shouldParse() {
        Double x = Double.parseDouble("1,0");
    }

    @Test
    public void test() {
        int x = 5;

        double d = d(x);
    }

    public double d(Object value) {
        System.out.println(value.getClass());
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        return 0;
    }
}
