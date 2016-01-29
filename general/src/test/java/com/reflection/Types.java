package com.reflection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

/**
 *
 */
public class Types {

    @Test
    public void test() throws NoSuchFieldException {
        Field variable = Testing.class.getDeclaredField("booleanVariable");

        boolean x = variable.getType().equals(boolean.class);
        Assert.assertTrue(x);
    }

    private class Testing {
        boolean booleanVariable;
    }
}
