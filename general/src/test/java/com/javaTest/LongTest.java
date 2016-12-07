package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */

public class LongTest {

    @Test
    public void performanceAutoboxing() {
        long sum = 0L;
        for (long i=0; i< Integer.MAX_VALUE; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
