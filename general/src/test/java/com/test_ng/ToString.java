package com.test_ng;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 *
 */
public class ToString {

    private final Map<Class<?>, Function<Object, Object>> mapper = Maps.newHashMap();
    private String arrayDelimiter = ", ";

    public String apply(Object source) {
        if (source == null) {
            return "null";
        }

        if (isArray(source)) {
            return mapArray((Object[]) source);

        } else {
            Object original = source;
            source = internalApply(source);
            if (source != original && !original.getClass().equals(String.class)) {
                source = apply(source);
            }
        }

        return String.valueOf(source);
    }

    private Object internalApply(Object source) {
        return mapper.getOrDefault(source.getClass(), Function.identity()).apply(source);
    }

    public ToString arrayDelimiter(String delimiter) {
        this.arrayDelimiter = delimiter;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> ToString map(Class<T> type, Function<T, Object> typeMapper) {
        this.mapper.put(type, (Function<Object, Object>) typeMapper);
        return this;
    }

    @SafeVarargs
    public final <T> ToString map(Class<T> type, Function<T, Object>... typeMappers) {
        return map(type, t -> Arrays.stream(typeMappers).map(f -> f.apply(t)).toArray());
    }

    private String mapArray(Object[] array){
        StringJoiner joiner = new StringJoiner(arrayDelimiter);
        for (Object v : array) {
            String str = apply(v);
            joiner.add(str);
        }
        return joiner.toString();
    }

    private boolean isArray(Object v) {
        return v != null && v.getClass().isArray();
    }
}
