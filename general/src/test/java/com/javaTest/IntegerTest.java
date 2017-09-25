package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class IntegerTest {

    @Test
    public void shouldParse() {
        Integer x = Integer.parseInt("2.0");
    }
}
