package com.stringbuilder;


import org.testng.annotations.*;

public class StringVsStringBuilder {
    long start;
    long startTest;


    @BeforeTest
    public void beforeTest(){
        startTest = System.currentTimeMillis();
        System.out.println("Test begin: ");
    }

    @BeforeMethod
    public void before(){
        start = System.currentTimeMillis();
        System.out.println("Method begin: ");
    }

    @Test (dataProvider = "number-test-provider", timeOut = 10000)
    public void performanceString(int n) {
        System.out.println("-- performance: String");
        String s = "";
        for (int i=0; i<n; i++) {
            s += " a trivial string " + i + "\n";
        }
    }


    @Test (dataProvider = "number-test-provider", timeOut = 10000)
    public void performanceStringBuilder(int n) {
        System.out.println("-- performance: StringBuilder");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            sb.append(" a trivial string ");
            sb.append(String.valueOf(i));
            sb.append("\n");
        }
        String s = sb.toString();
    }

    @DataProvider (name = "number-test-provider")
    public Object[][] nTestProvider() {
        return new Object[][] {{10000}};
    }

    @AfterMethod
    public void afterTest() {
        double seg = getDuration(start);

        System.out.println("Method end. ");
        System.out.println("time: " + seg );
    }

    @AfterTest
    public void after() {
        double seg = getDuration(startTest);

        System.out.println("Test end. ");
        System.out.println("time: " + seg );
    }

    double getDuration(long ini) {
        long end = System.currentTimeMillis();
        long time = end - ini;

        double seg = time / 1000.0;

        return seg;
    }
}
