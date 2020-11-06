package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class BigDecimalTest {

    @Test
    public void shouldTetDigit() {
        BigDecimal q = new BigDecimal("0", new MathContext(1));

        q = q.add(BigDecimal.valueOf(0.14456));
        q = q.add(BigDecimal.valueOf(2));
        q = q.add(BigDecimal.valueOf(123));

        System.out.println(q);
    }

    @Test
    public void sumUsingScale() {

        BigDecimal a = new BigDecimal("0.119").setScale(3, RoundingMode.HALF_UP);
        BigDecimal b = new BigDecimal(0.1);//.setScale(3, RoundingMode.HALF_UP);

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.add(b));
        System.out.println(b.add(a));
    }

    @Test
    public void roundHalfUp() {
        double d = 1.0005;
        BigDecimal bd = BigDecimal.valueOf(d);
        Assert.assertEquals(bd.setScale(3, RoundingMode.HALF_UP).toString(), "1.001");
    }



    @DataProvider
    public Object[][] provider() {
        return new Object[][]{
                {array(1, 2, 3, 4, 5), 2, "3, 4, 5, 1, 2"},
                {array(1, 2, 3, 4, 5), 3, "4, 5, 1, 2, 3"},

                {array(1, 2, 3, 4, 5, 6), 3, "4, 5, 6, 1, 2, 3"}
        };
    }

    @Test(dataProvider = "provider")
    public void assertSwap(List<Integer> list, int m, String expected) {

        List<Integer> mElements = new ArrayList<>(list.subList(0, m));
        List<Integer> first = new ArrayList<>(list.subList(m, list.size()));
        list.clear();
        list.addAll(first);
        list.addAll(mElements);

        Assert.assertEquals(list.toString(), "[" + expected + "]");
    }

    public List<Integer> array(Integer ...values) {
        return new ArrayList<>(Arrays.asList(values));
    }
}
