package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class NullOperations {

    @Test
    public void multiplyingByNull() {
        Float x = null;
        Float y = 4.0f;
        Float c = x * y;
    }
}
