package com.string;

import com.google.common.collect.Maps;
import com.javaTest.PrimitiveTypes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class CustomToString {

    private final Map<Class<?>, Function<Object, Object>> mappings = Maps.newHashMap();
    private final IterableSettings iterableSettings = new IterableSettings(", ", "", "");
    private final IterableSettings beanSettings = new IterableSettings(", ", "", "");
    private String nullMapping = "null";

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

    public CustomToString mapNullTo(String nullMapping) {
        this.nullMapping = nullMapping;
        return this;
    }

    public CustomToString delimiter(String delimiter) {
        return delimiter(delimiter, "", "");
    }

    public CustomToString delimiter(String delimiter, String begin, String end) {
        this.iterableSettings.setDelimiter(delimiter, begin, end);
        return this;
    }

    public CustomToString beanDelimiter(String delimiter) {
        return beanDelimiter(delimiter, "", "");
    }

    public CustomToString beanDelimiter(String delimiter, String begin, String end) {
        this.beanSettings.setDelimiter(delimiter, begin, end);
        return this;
    }

    public CustomToString sorted() {
        iterableSettings.sorted = true;
        return this;
    }

    @SuppressWarnings("unchecked")
    public String apply(Object source) {
        if (source == null) return applyNull();

        Object value;
        if (isPrimitive(source)) {
            value = applyMapping(source);

        } else if (isArray(source)) {
            value = applyArray((Object[]) source);

        } else if (isCollection(source)) {
            value = applyCollection((Collection<Object>) source);

        } else if (source instanceof Bean) {
            value = applyBean((Bean) source);

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

    private String applyNull() {
        return nullMapping;
    }

    private String applyArray(Object[] array) {
        return applyStream(Arrays.stream(array), iterableSettings);
    }

    private String applyCollection(Collection<Object> c) {
        return applyStream(c.stream(), iterableSettings);
    }

    private String applyBean(Bean bean) {
        return applyStream(Arrays.stream(bean.values), beanSettings);
    }

    private String applyStream(Stream<Object> stream, IterableSettings settings) {
        Stream<String> s = stream.map(this::apply);
        if (settings.sorted) {
            s = s.sorted();
        }
        return s.collect(settings.collector());
    }

    private boolean isPrimitive(Object source) {
        return PrimitiveTypes.isPrimitive(source);
    }

    private boolean isArray(Object source) {
        return source != null && source.getClass().isArray();
    }

    private boolean isCollection(Object source) {
        return source instanceof Collection;
    }

    private boolean isMap(Object source) {
        return source instanceof Map;
    }

    private boolean isBean(Object source) {
        return !(source == null || isPrimitive(source) || isArray(source) || isCollection(source) || isMap(source));
    }

    private static class Bean {
        private final Object[] values;

        private Bean(Object[] values) {
            this.values = values;
        }
    }

    private static class IterableSettings {
        private String delimiter;
        private String begin;
        private String end;
        private boolean sorted;

        public IterableSettings(String delimiter, String begin, String end) {
            this.delimiter = delimiter;
            this.begin = begin;
            this.end = end;
        }

        public void setDelimiter(String delimiter, String begin, String end) {
            this.delimiter = delimiter;
            this.begin = begin;
            this.end = end;
        }

        private Collector<CharSequence, ?, String> collector() {
            return Collectors.joining(delimiter, begin, end);
        }

    }
}
