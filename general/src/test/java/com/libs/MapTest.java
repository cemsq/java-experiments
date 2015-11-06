package com.libs;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MapTest {

    @Test
    public void testing_duplicatedKeys() {
        Map<String, String> map = new HashMap<>();

        map.put("1", "first element");
        map.put("2", "second element");
        map.put("3", "third element");
        map.put("4", "fourth element");
        map.put("4", "fourth element");

        Assert.assertEquals(map.size(), 4);
    }

    @Test
    public void testing_nullValues() {
        Map<String, String> map = new HashMap<>();

        map.put("a", null);

        Assert.assertEquals(map.size(), 1);
    }
}
