package com.javaTest;

import com.google.common.collect.Maps;
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
    public void shouldRemoveWhenPutNull() {
        Map<String, String> map = Maps.newHashMap();
        map.put("k1", "my value");

        map.put("k1", null);

        Assert.assertEquals(map.size(), 1, "map.size after put");
    }

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

    @Test
    public void testing() {
        ValueStore store = new ValueStore();
        store.set("1", 5);
        store.set("2", "string");
        store.set("3", 4.5);

        Integer i = store.get("1", Integer.class);
        String s = store.get("2", String.class);
        Double d = store.get("3", Double.class);
    }

    class ValueStore {
        Map<String, Object> map = new HashMap<>();

        public ValueStore() {

        }

        public ValueStore set(String key, Object value) {
            map.put(key, value);
            return this;
        }

        public <T> T get(String key, Class<T> clazz) {
            if (map.containsKey(key)) {
                return (T) map.get(key);
            }

            return null;
        }
    }
}
