package properties.grabber;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import properties.exception.FieldFormatException;
import properties.exception.FieldNotFoundException;

import java.lang.reflect.Field;

/**
 *
 */
public abstract class FieldGrabberHelper {
    private FieldGrabberHelper() {}

    public static Field getField(Class clazz, String fieldName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(fieldName), "fieldName should be not empty or not null");
        Preconditions.checkNotNull(clazz, "clazz should be not null");

        String className = clazz.getName();
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        if (field == null) {
            throw new FieldNotFoundException("In Class: " + className + ". fieldName : " + fieldName);
        }

        return field;
    }

    public static Object getFieldValue(Field field , Object obj) {
        Object value;
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            value = field.get(obj);
            field.setAccessible(accessible);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Something goes really wrong", e);
        }
        return value;
    }

    public static void setFieldValue(Field field, Object object, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            field.set(object, value);

            field.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Something goes really wrong", e);
        }
    }

    public static String[] parseFieldName(String fieldName) {
        checkFieldName(fieldName);

        String field = fieldName;
        int point = fieldName.indexOf(".");

        if (point > 0) {
            field = fieldName.substring(0, point);
            fieldName = fieldName.substring(point + 1);
        } else {
            fieldName = "";
        }

        return new String[]{
                field,
                fieldName
        };
    }

    private static void checkFieldName(String fieldName) {
        if (Strings.isNullOrEmpty(fieldName)) {
            throw new FieldFormatException("Empty fieldName");
        }
        if (fieldName.endsWith(".")) {
            throw new FieldFormatException("fieldName should not ent with dot");
        }
    }
}
