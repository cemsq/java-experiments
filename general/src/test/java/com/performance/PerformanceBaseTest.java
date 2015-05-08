package com.performance;

import org.testng.annotations.*;

/**
 *
 */
public class PerformanceBaseTest {
    long start;
    long startTest;


    @BeforeTest
    public void beforeTest(){
        startTest = System.currentTimeMillis();
        System.out.println("Test begin: ");
    }

    @BeforeMethod
    public void beforeMethod(){
        start = System.currentTimeMillis();
        System.out.println("Method begin: ");
    }

    @AfterMethod
    public void afterMethod() {
        double seg = getDuration(start);

        System.out.println("Method time: " + seg );
    }

    @AfterTest
    public void afterTest() {
        double seg = getDuration(startTest);

        System.out.println("Test time: " + seg );
    }

    public double getDuration(long ini) {
        long end = System.currentTimeMillis();
        long time = end - ini;

        return time / 1000.0;
    }
}
