package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class NullOperations {

    @Test
    public void multiplyingByNull() {
        Float x = null;
        Float y = 4.0f;
        Float c = x * y;
    }

    @Test
    public void convertingFromNullToString() {
        Object object = null;
        String value = String.valueOf(object);

        Assert.assertTrue(value.length() >= 0);
    }
}
