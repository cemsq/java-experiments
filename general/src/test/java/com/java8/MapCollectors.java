package com.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 */
public class MapCollectors {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("key1");
        Map<String, Object> map = list.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> null));
    }

    @Test
    public void allowNullAsValue() {
        List<Paar> list = Lists.newArrayList(
                Paar.create(1, "a"),
                Paar.create(2, "b"),
                Paar.create(3, null)
        );

        Map<Integer, String> map = list.stream()
                .collect(Collectors.toMap(
                        Paar::getKey,
                        Paar::getValue
                ));
    }

    @Test
    public void allowNullAsKey() {

        int size = 10000;
        List<Paar> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < size; j++) {
                list.add(Paar.create(i, String.valueOf(i)));
            }
        }

        list.add(Paar.create(null, "my null key 0"));
        list.add(Paar.create(null, "my null key 1"));
        list.add(Paar.create(null, "my null key 2"));

//        Map<Integer, List<Paar>> grouped = list
//                .stream()
//                .collect(Collectors.groupingBy(Paar::getKey, Collectors.toList()));

        Map<Integer, List<Paar>> grouped = groupBy(list, Paar::getKey);

        Assert.assertEquals(grouped.size(), 11, "groups size");
        Assert.assertEquals(grouped.get(0).size(), size, "group 0");
        Assert.assertEquals(grouped.get(9).size(), size, "group 9");

        List<Paar> nullKey = grouped.get(null);
        Assert.assertEquals(nullKey.size(), 3, "null key size");
    }

//    public static <T, K, U>
//    Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
//                                    Function<? super T, ? extends U> valueMapper) {
//        return toMap(keyMapper, valueMapper, throwingMerger(), HashMap::new);
//    }
//
//    public static <T, K, U, M extends Map<K, U>>
//    Collector<T, ?, M> toMap(Function<? super T, ? extends K> keyMapper,
//                             Function<? super T, ? extends U> valueMapper,
//                             BinaryOperator<U> mergeFunction,
//                             Supplier<M> mapSupplier) {
//        BiConsumer<M, T> accumulator
//                = (map, element) -> map.merge(keyMapper.apply(element),
//                valueMapper.apply(element), mergeFunction);
//        return new Collectors.CollectorImpl<>(mapSupplier, accumulator, mapMerger(mergeFunction), CH_ID);
//    }

    public static <T, K> Map<K, List<T>> groupBy(List<T> elements, Function<? super T, ? extends K> classifier) {
        if (elements == null) {
            return Maps.newHashMap();
        }

        // Do not use the Collectors.groupingBy because the created map does not allow nulls as key, a HashMap does it
        Map<K, List<T>> map = Maps.newHashMap();
        elements.stream()
                .filter(Objects::nonNull)
                .forEach(e -> {
                    K key = classifier.apply(e);
                    List<T> list = map.computeIfAbsent(key, k -> Lists.newArrayList());
                    list.add(e);
                });

        return map;
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
    }

    public static class Paar {
        private Integer key;
        private String value;

        public static Paar create(Integer k, String v) {
            return new Paar(k, v);
        }

        public Paar(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
