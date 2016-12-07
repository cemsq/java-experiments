package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Supplier;

/**
 *
 */
public class LambdasTest {

    private Integer var;

    @Test
    public void test() {

        var = 15;
        Supplier<Integer> varSupplier = () -> this.var;

        this.var = 35;

        Assert.assertEquals(varSupplier.get().intValue(), 35);
    }
}
