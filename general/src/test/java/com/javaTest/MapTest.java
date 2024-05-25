package com.javaTest;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.Math.floor;
import static java.lang.Math.log;

/**
 *
 */
public class MapTest {

    @Test
    public void putIfAbsent_onNullValue() {
        Map<Integer, String> map = Maps.newHashMap();
        Supplier<String> value = () -> map.get(1);

        map.put(1, "val1");
        map.putIfAbsent(1, "val2");
        Assert.assertEquals(value.get(), "val1", "val1");

        map.put(1, null);
        Assert.assertNull(value.get(), "null");
        Assert.assertEquals(map.size(), 1, "size with null value");

        map.putIfAbsent(1, "new value");
        Assert.assertEquals(value.get(), "new value", "putIfAbsent on null");
    }

    @Test
    public void shouldRemoveWhenPutNull() {
        Map<String, String> map = Maps.newHashMap();
        map.put("k1", "my value");

        map.put("k1", null);

        Assert.assertEquals(map.size(), 1, "map.size after put");
    }

    @Test
    public void shouldGrow_mapHasAnInitialCapacity() {
        Map<Integer, String> map = Maps.newHashMapWithExpectedSize(3);
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        map.put(4, "d");
        map.put(5, "e");
        map.put(6, "f");
        map.put(7, "g");

        Assert.assertEquals(map.size(), 7, "size");
    }

    @Test
    public void linkedMapReinsertion() {
        Map<Integer, String> map = Maps.newLinkedHashMap();
        map.put(1, "a");
        map.put(2, null);
        map.put(3, "c");

        assertEquals(map.values(), "a, null, c");

        map.put(2, "b");
        assertEquals(map.values(), "a, b, c");
    }

    @Test
    public void shouldAlphabet() {
        System.out.println(getString(28));
    }

    private static String getString(int n) {
        char[] buf = new char[(int) floor(log(25 * (n + 1)) / log(26))];
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('A' + n % 26);
            n /= 26;
        }
        return new String(buf);
    }

    @Test
    public void removeFromValues() {
        Map<Integer, String> map = Maps.newLinkedHashMap();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        map.values().remove("c");
        assertEquals(map.values(), "a, b");
        assertEquals(map.keySet(), "1, 2");
    }

    @Test
    public void shouldContainsByValue() {
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

    private void assertEquals(Collection<?> list, String expected) {
        String values = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        Assert.assertEquals(values, expected);
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
