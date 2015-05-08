package com.test_ng;

import org.testng.annotations.*;

/**
 * Created by César Mora on 08.10.2014.
 */
public class testNG {



    @BeforeTest
    public void beforeTest(){
        System.out.println("before test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("    before class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("        before method");
    }

    @Test
    public void test1(){
        System.out.println("            test 1");
    }

    @Test
    public void test2(){
        System.out.println("            test 2");
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void test3() {
        System.out.println("            null pointer test");
        throw new NullPointerException();
    }

    @Test
    public void test4() {
        System.out.println("            test 4");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("        after method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("    after class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after test");
    }


}
