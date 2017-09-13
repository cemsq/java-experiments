package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class BooleanTest {

    @DataProvider
    public Object[][] provider() {
        return new Object[][] {
                {"true", true},
                {"True", true},
                {"tRUe", true},

                {"yes", false},

                {"false", false},
                {"False", false},
                {"asdf", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "provider")
    public void convertingStringToBoolean(String value, boolean expected) {
        boolean result = Boolean.valueOf(value);

        Assert.assertEquals(result, expected,
                "error testing [value: '" + value + "']");
    }

    @Test
    public void instanceOfBoolean() {
        boolean a = true;
        Object obj = a;

        Assert.assertTrue(obj instanceof Boolean);
    }
}
