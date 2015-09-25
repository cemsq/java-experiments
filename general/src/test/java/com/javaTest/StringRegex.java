package com.javaTest;

import org.testng.Assert;
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
}
