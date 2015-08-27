package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class ConditionOnForLoopTest {

    private static int count;

    private static int N;

    @Test
    public void shouldCountInForCondition() {
        count = 0;
        N = 10;

        for (int i=0; condition(i); i++) {

        }

        Assert.assertTrue(count > 1, "condition is one time evaluated");
    }

    @Test
    public void shouldCountInForCondition2() {
        count = 0;
        N = 10;

        for (int i=0; i < getN(); i++) {

        }

        Assert.assertTrue(count > 1, "condition is one time evaluated");
    }

    private boolean condition(int i) {
        count++;
        return i < N;
    }

    private int getN() {
        count++;
        return N;
    }
}
