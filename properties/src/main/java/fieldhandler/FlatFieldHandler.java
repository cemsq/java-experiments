package fieldhandler;

import reflection.Reflections;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 *
 */
public class FlatFieldHandler implements FieldHandler {

    Method getter;
    Method setter;

    FlatFieldHandler(Class clazz, String fieldName) {
        getter = Reflections.getter(clazz, fieldName);
        setter = Reflections.setter(clazz, fieldName);
    }

    @Override
    public Object get(Object obj) {
        return Reflections.invoke(getter, obj);
    }

    @Override
    public void set(Object obj, Object value) {
        Reflections.invoke(setter, obj, value);
    }
}
