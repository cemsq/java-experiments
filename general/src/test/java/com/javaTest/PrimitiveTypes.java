package com.javaTest;

import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.AtomicDouble;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public abstract class PrimitiveTypes {

    private static final Set<Class<?>> BOOLEAN_TYPES = ImmutableSet.of(
            Boolean.class,
            Boolean.TYPE
    );

    private static final Set<Class<?>> NUMERIC_TYPES = ImmutableSet.of(
            Integer.TYPE,
            Integer.class,
            BigInteger.class,
            BigDecimal.class,
            AtomicInteger.class,
            AtomicLong.class,
            AtomicDouble.class,
            Long.TYPE,
            Long.class,
            Double.TYPE,
            Double.class,
            Float.TYPE,
            Float.class,
            Byte.TYPE,
            Byte.class,
            Short.TYPE,
            Short.class
    );

    private static final Set<Class<?>> STRING_TYPES = ImmutableSet.of(
            Character.TYPE,
            Character.class,

            String.class
    );

    private static final Set<Class<?>> PRIMITIVE_TYPES = ImmutableSet.<Class<?>>builder()
            .addAll(BOOLEAN_TYPES)
            .addAll(NUMERIC_TYPES)
            .addAll(STRING_TYPES)
            .build();



    private PrimitiveTypes() {
        throw new UnsupportedOperationException("You're in trouble, buddy :(");
    }

    public static boolean isPrimitive(Object obj) {
        return obj != null && PRIMITIVE_TYPES.contains(obj.getClass());
    }

    public static boolean isBoolean(Object obj) {
        return obj != null && BOOLEAN_TYPES.contains(obj.getClass());
    }

    public static boolean isNumeric(Object obj) {
        return obj != null && NUMERIC_TYPES.contains(obj.getClass());
    }

    public static boolean isString(Object obj) {
        return obj != null && STRING_TYPES.contains(obj.getClass());
    }
}
