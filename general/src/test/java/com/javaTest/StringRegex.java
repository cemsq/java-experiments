package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class StringRegex {

    @Test
    public void shouldMatches() {
        String str = "it should throw an exception";

        Assert.assertTrue(str.contains("throw"));
    }

    @DataProvider(name = "patternProvider")
    public Object[][] patternProvider() {
        return new Object[][]{
                {"0", "0", true},
                {"10", ".*0.*", true},
                {"101", ".*0.*", true},

                {"{0}", ".*\\{[0-9]\\}.*", true},
                {"{10}", ".*\\{[0-9]*\\}.*", true},
        };
    }

    @Test(dataProvider = "patternProvider")
    public void shouldMatch(String source, String pattern, boolean expected) {
        Assert.assertEquals(source.matches(pattern), expected,
                String.format("not match. Source %s, pattern %s", source, pattern));
    }

    @DataProvider(name = "replaceProvider")
    public Object[][] replaceProvider() {
        return new Object[][]{
                {"my {0}", "\\{[0-9]*\\}", "XXX", "my XXX"},
                {"my {10}", "\\{[0-9]*\\}", "XXX", "my XXX"},
                {"my {0}, hello {10}", "\\{[0-9]*\\}", "XXX", "my XXX, hello XXX"}
        };
    }

    @Test(dataProvider = "replaceProvider")
    public void shouldReplace(String source, String pattern, String replacement, String expected) {
        String value = source.replaceAll(pattern, replacement);
        Assert.assertEquals(value, expected);
    }
}
