package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class CastingsTest {

    @Test
    public void test() {
        Integer x = 2;
        String y = cast(x);

        System.out.println("the value is: " + y);
    }

    public static <T> T cast(Object obj) {
        if (obj != null) {
            return (T)obj;
        }

        return null;
    }
}
