package properties.grabber;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import properties.exception.ClassHelperException;
import properties.exception.FieldNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public class ClassHelper {

    /**
     * Try to create an instance from clazz.
     */
    public static Object createInstance(Class clazz) {
        Constructor constructor = getEmptyConstructor(clazz);
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ClassHelperException("could not instantiate class: " + clazz, e);
        }
    }

    /**
     * Search a Constructor without parameters for clazz.
     * It throws an Exception if such Constructor is not found
     */
    public static Constructor getEmptyConstructor(Class clazz) {
        for (Constructor constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        throw new ClassHelperException("Empty constructor not found in class: " + clazz.getName());
    }

    /**
     * Returns a Field object that reflects the specified declared field of the class.
     * If clazz has no such Field, it search in SuperClass of clazz.
     */
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

    /**
     * Returns the value of the field represented by Field, on the specified object
     */
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

    /**
     * Sets the field represented by Field object on the specified object argument to the specified new value.
     */
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
}
