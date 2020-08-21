package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class IntegerTest {

    @DataProvider
    public Object[][] ceilProvider() {
        return new Object[][]{
                {1.2, 2},
                {1.3, 2},
                {1.45, 2},
                {1.5, 2},
                {1.7, 2},
        };
    }

    @Test(dataProvider = "ceilProvider")
    public void ceil(double from, double expected) {
        Assert.assertEquals(Math.ceil(from), expected, String.format("%s -> %s", from, expected));
    }

    @DataProvider
    public Object[][] floorProvider() {
        return new Object[][]{
                {1.2, 1},
                {1.3, 1},
                {1.45, 1},
                {1.5, 1},
                {1.7, 1},
        };
    }

    @Test(dataProvider = "floorProvider")
    public void floor(double from, double expected) {
        Assert.assertEquals(Math.floor(from), expected, String.format("%s -> %s", from, expected));
    }

    @DataProvider
    public Object[][] roundProvider() {
        return new Object[][]{
                {1.2, 1},
                {1.3, 1},
                {1.49, 1},
                {1.5, 2},
                {1.7, 2},
        };
    }

    @Test(dataProvider = "roundProvider")
    public void round(double from, int expected) {
//        Assert.assertEquals((double) Math.round(from), expected, String.format("%s -> %s", from, expected));
        Assert.assertEquals(Double.valueOf(from).intValue(), expected, String.format("%s -> %s", from, expected));
    }
}
