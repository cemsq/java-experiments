package com.cgm.storm.utils.properties.adapter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import java.math.BigDecimal;

/**
 *
 */
public class TypeConverter {

    private TypeConverter() {
    }

    public static Object getRealValueAsObject(Class clazz, String value) {
        // unicode string type
        if (clazz == String.class || StringBuilder.class.isAssignableFrom(clazz)) {
            return value;
        }

        // unicode character
        if (clazz == Character.class || clazz == char.class) {
            return value.charAt(0);
        }

        // boolean types
        if (clazz == Boolean.class || clazz == boolean.class) {
            return Boolean.valueOf(value);
        }

        // 32bit integer
        if (clazz == Integer.class || clazz == int.class) {
            return Integer.valueOf(value);
        }

        // 16bit integer
        if (clazz == Short.class || clazz == short.class) {
            return Short.valueOf(value);
        }

        // 64bit integer
        if (clazz == Long.class || clazz == long.class) {
            return Long.valueOf(value);
        }

        // floating point
        if (clazz == Float.class || clazz == float.class) {
            return Float.valueOf(value);
        }

        // double precision floating point
        if (clazz == Double.class || clazz == double.class) {
            return Double.valueOf(value);
        }

        // decimal type
        if (clazz == BigDecimal.class) {
            return BigDecimal.valueOf(Double.valueOf(value));
        }

        // byte
        if (clazz == Byte.class || clazz == byte.class) {
            return Byte.valueOf(value);
        }

        if (clazz == LocalDate.class) {
            return LocalDate.parse(value);
        }

        // convert to timestamp not to loose precision
        if (clazz == LocalDateTime.class) {
            return LocalDateTime.parse(value);
        }

        // timespan
        if (clazz == Period.class) {
            return Period.parse(value);
        }

        return value;
    }
}
