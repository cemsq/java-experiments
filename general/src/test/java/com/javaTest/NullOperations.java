package com.javaTest;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 *
 */
public class NullOperations {

    @Test(expectedExceptions = NullPointerException.class)
    public void multiplyingByNull() {
        Float x = null;
        Float y = 4.0f;
        Float c = x * y;
    }

    @Test
    public void convertingFromNullToString() {
        Object object = null;
        String value = String.valueOf(object);

        Assert.assertTrue(value.length() >= 0);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void comparingWithNull() {
        Integer x = 5;
        Integer y = null;

        if (x > y) {

        }
    }

    @Test
    public void shouldBeFalseBecauseBooleanIsNull() {
        Boolean x = Boolean.valueOf(null);

        Assert.assertEquals(x, Boolean.FALSE);
    }

    @Test
    public void insertNullInMap() {
        Map<String, String> map = Maps.newHashMap();
        map.put("key1", null);
    }

    @Test
    public void instanceOf() {
        Assert.assertEquals(null instanceof String, false);
    }

    @Test
    public void testNullArgs() {
        methodWithArguments(null);
    }

    @Test
    public void testEmptyArgs() {
        methodWithArguments();
    }

    public void methodWithArguments(String ... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
