package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class DoubleTest {

    @Test
    public void shouldParse() {
        Double x = Double.parseDouble("1,0");
    }
}
