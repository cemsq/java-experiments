package com;

import org.testng.annotations.Test;

/**
 *
 */
public class MainTest {

    @Test
    public void test() {
        System.out.println("my var: " + System.getProperty("my_var"));
    }
}
