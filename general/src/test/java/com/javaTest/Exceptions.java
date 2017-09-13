package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class Exceptions {

    @Test
    public void shouldRetainValueBeforeException() {
        Integer x = null;
        try {
            x = 5;

            throw new NullPointerException();
        } catch (Exception e) {

            Assert.assertEquals(x, Integer.valueOf(5));
        }
    }

    @Test
    public void tryCatchWithExceptionFinally() {
        try {
            int x = 5/0;


        } catch (Exception e) {
            System.out.println("catch");
            throw new RuntimeException("catch", e);

        } finally {
            System.out.println("finally");
        }
    }
}

