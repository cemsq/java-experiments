package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class SwitchTest {

    @Test
    public void test() {

        switch (value()) {
            case 1: break;
            case 2: break;
                default:
                    System.out.println("null returned");
        }
    }

    public Integer value() {
        return null;
    }
}
