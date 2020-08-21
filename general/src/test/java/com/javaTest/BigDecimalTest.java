package com.javaTest;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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


}
