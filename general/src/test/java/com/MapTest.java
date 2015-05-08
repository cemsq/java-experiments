package com;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MapTest {
    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();

        map.put("1", "aa");
        map.put("1", "bb");
        map.put("2", "cc");
        map.put("3", "dd");

        Assert.assertEquals(map.size(), 3);
        Assert.assertTrue(map.containsValue("bb"));
    }
}
