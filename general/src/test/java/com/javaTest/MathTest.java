package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class MathTest {

    @Test
    public void round() {

        for (double d = 0; d < 1; d += 0.01) {
            System.out.println(String.format("round(%s) = %s", d, Math.round(d)));
        }
    }

    @Test
    public void floorDiv() {
        double ceil = Math.ceil(2.2);

        System.out.println(ceil);
    }

    static float roundOff(float x, int position)
    {
        float a = x;
        double temp = Math.pow(10.0, position);
        a *= temp;
        a = Math.round(a);
        return (a / (float)temp);
    }

    @Test
    public void roundTest()
    {
        float a = roundOff(0.0009434f,3);
        System.out.println("a="+a+" (a % .001)="+(a % 0.001));
        int count = 0, errors = 0;
        for (double x = 0.0; x < 1; x += 0.0001)
        {
            count++;
            double d = x;
            int scale = 2;
            double factor = Math.pow(10, scale);
            d = Math.round(d * factor) / factor;
            if ((d % 0.01) != 0.0)
            {
                System.out.println(d + " " + (d % 0.01));
                errors++;
            }
        }
        System.out.println(count + " trials " + errors + " errors");
    }
}
