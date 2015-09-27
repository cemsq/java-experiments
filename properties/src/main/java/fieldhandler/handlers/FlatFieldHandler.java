package fieldhandler.handlers;

import fieldhandler.FieldHandler;
import reflection.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class FlatFieldHandler implements FieldHandler {

    private Class fieldType;
    private boolean isArray;
    private Method getter;
    private Method setter;

    FlatFieldHandler(Class clazz, String fieldName) {
        Field f = Reflections.getField(clazz, fieldName);

        isArray = f.getType().equals(List.class);
        fieldType = Reflections.getType(f);
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

    public Class getFieldType() {
        return fieldType;
    }

    public boolean isArray() {
        return isArray;
    }
}
