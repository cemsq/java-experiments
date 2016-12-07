package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class StringRegexTest {

    @Test
    public void matchNumbers() {
        boolean matches = "12365".matches("[0-9]*");

        Assert.assertTrue(matches);
    }

    @Test
    public void matchParOrSpace() {
        boolean matches = "   ".matches("[ (]*");

        Assert.assertTrue(matches);
    }
}
