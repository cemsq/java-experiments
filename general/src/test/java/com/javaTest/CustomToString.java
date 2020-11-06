package com.javaTest;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public class CustomToString {

    private final Map<Class<?>, Function<Object, Object>> mappings = Maps.newHashMap();


    @SafeVarargs
    public final <T> CustomToString map(Class<T> type, Function<T, Object>... getters) {
        return map(type, t -> Arrays.stream(getters)
                .map(g -> g.apply(t))
                .toArray());
    }

    @SuppressWarnings("unchecked")
    public final <T> CustomToString map(Class<T> type, Function<T, Object> mapper) {
        this.mappings.put(type, (Function<Object, Object>) mapper);
        return this;
    }

    public String apply(Object source) {
        if (source == null) return "null";

        Object value;
        if (isPrimitive(source)) {
            value = applyMapping(source);

        }else if (isArray(source)) {
            value = applyArray((Object[]) source);

        } else {
            value = applyMapping(source);
            if (value != source) {
                value = apply(value);
            }
        }
        return String.valueOf(value);
    }

    private Object applyMapping(Object source) {
        Object mapped = null;
        if (source != null) {
            mapped = mappings.getOrDefault(source.getClass(), Function.identity()).apply(source);
        }

        return mapped;
    }

    private boolean isPrimitive(Object source) {
        return PrimitiveTypes.isPrimitive(source);
    }

    private boolean isArray(Object source) {
        return source != null && source.getClass().isArray();
    }

    public String applyArray(Object[] array) {
        return Arrays.stream(array)
                .map(this::apply)
                .collect(Collectors.joining(", "));
    }
}
