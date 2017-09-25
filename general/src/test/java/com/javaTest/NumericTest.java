package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class NumericTest {

    @Test
    public void test() {
        Integer x = 5;
        Float y = 3f;

        Double f = f(x, y);

        Assert.assertEquals(f.equals(8), true);
    }

    private Double f(Object source, Object value) {
        double a = Double.parseDouble(source.toString());
        double b = Double.parseDouble(value.toString());

        return a + b ;
    }
}
