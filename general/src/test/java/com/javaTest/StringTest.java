package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class StringTest {



    @DataProvider
    public Object[][] splitProvider() {
        return new Object[][] {
                {"x.y.z", "\\.", 3},
                {"x", "\\.", 1}
        };
    }

    @Test(dataProvider = "splitProvider")
    public void shouldSplit(String str, String regex, int expected) {
        String[] split = str.split(regex);
        Assert.assertEquals(split.length, expected);

    }

    @Test
    public void shouldStartsWith() {
        String http = "http://";

        String trueHttp = "http://10.20.03.3";
        Assert.assertTrue(http.startsWith(trueHttp));

        Assert.assertFalse(http.startsWith(null));
    }

    @Test
    public void shouldCompareStrings() {
        String a = "31";
        String b = "15";

        Assert.assertTrue(a.compareTo(b) > 0);
    }
}
