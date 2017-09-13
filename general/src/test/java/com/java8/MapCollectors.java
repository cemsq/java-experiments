package com.java8;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
