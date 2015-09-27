package reflection;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import reflection.exception.ConstructorNotFoundException;
import reflection.exception.FieldNotFoundException;
import reflection.exception.ReflectionsException;
import util.StringsUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class Reflections {

    private Reflections() {}

    /**
     * Try to create an instance from clazz.
     */
    public static<T> T createInstance(Class<T> clazz) {
        Constructor constructor = getEmptyConstructor(clazz);
        try {
            return (T)constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionsException("could not instantiate class: " + clazz, e);
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
        throw new ConstructorNotFoundException(clazz);
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
            throw new FieldNotFoundException(className, fieldName);
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
            throw new ReflectionsException("Something goes really wrong", e);
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
            throw new ReflectionsException("Something goes really wrong", e);
        }
    }

    public static Method getter(Class clazz, String field) {
        try {
            Field f = getField(clazz, field);

            return clazz.getMethod("get" + StringsUtil.capitalize(field));
        } catch (NoSuchMethodException e) {
            throw new ReflectionsException("Getter Method not found for field: " + field);
        }
    }

    public static Method setter(Class clazz, String field) {
        try {
            Field f = getField(clazz, field);

            return clazz.getMethod("set" + StringsUtil.capitalize(field), f.getType());
        } catch (NoSuchMethodException e) {
            throw new ReflectionsException("Setter Method not found for field: " + field);
        }
    }

    public static Object invoke(Method method, Object obj, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionsException("Unable to call method: " + method.getName(), e);
        }
    }

    public static Class getType(Field field) {
        if (field.getType().equals(List.class)) {
            return extractGenericType(field.getGenericType().getTypeName());
        } else {
            return field.getType();
        }
    }

    public static Class extractGenericType(String type) {
        int p1 = type.indexOf("<") + 1;
        int p2 = type.indexOf(">");

        if (p1 < p2) {
            String name = type.substring(p1, p2);
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new ReflectionsException("Class not found: " + name);
            }
        }
        throw new ReflectionsException(type + " is not a Generic Type");
    }
}
