package com.performance;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class IfInLoop extends PerformanceBaseTest {

    private static int length = 5;
    private static int nElements = 100000;
    private static List<String> list;
    static {
        list = new ArrayList<>();
        for (int i = 0; i < nElements; i++) {
            list.add(generateWord(length));
        }
    }

    public static String generateWord(int length) {
        StringBuilder ret = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            double r = Math.random();
            char n = (char) (int) (65.0 + r * (90.0 - 65.0));

            ret.append(n);
        }

        return ret.toString();
    }

    @DataProvider (name = "list-provider")
    public Object[][] getList() {
        return new Object[][]{{list}};
    }

    @Test (dataProvider = "list-provider")
    public void test_printing(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test (dataProvider = "list-provider")
    public void test_if(List<String> list) {
        int toCompare = 3;
        String comp = generateWord(length);
        String a = comp.substring(1,toCompare);

        int n = 0;
        int i = 0;
        for (String str : list) {
            String b = str.substring(1,toCompare);

            if (a.equals(b)) {
                System.out.println(a + "   " + b);
                comp += b;
                n++;
            }

            i++;
        }
        System.out.println("total of " + nElements + ": " + n);
    }

    @Test (dataProvider = "list-provider")
    public void test_withOut_if(List<String> list) {
        String comp = generateWord(length);

        for (String str : list) {

        }
    }
}
