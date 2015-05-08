package com.test_ng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class Parameters {

    @DataProvider
    public Object[][] provider() {
        Object[] test1 = new Object[] {"test 1", 2, 2, 4};
        Object[] test2 = new Object[] {"test 2", 2, 4, 8};
        Object[] test3 = new Object[] {"test 3", 2, 6, 12};
        Object[] test4 = new Object[] {"test 4", 2, 8, 16};

        return new Object[][]{test1, test2, test3, test4};
    }

    @Test(dataProvider = "provider")
    public void test(String name, int a, int b, int value) {
        System.out.println("receiving: " + name + " " + a + "*" + b + "=" + value);

        Assert.assertEquals(a*b, value);
    }
}
