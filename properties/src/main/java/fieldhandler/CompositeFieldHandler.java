package fieldhandler;

import reflection.Reflections;

import java.lang.reflect.Field;

/**
 *
 */
public class CompositeFieldHandler implements FieldHandler {

    private Class fieldType;
    private FieldHandler owner;
    private FieldHandler composite;

    CompositeFieldHandler(Class clazz, String field, String composite) {
        Field f = Reflections.getField(clazz, field);
        fieldType = f.getType();

        this.owner = FieldHandlers.create(clazz, field);
        this.composite = FieldHandlers.create(f.getType(), composite);
    }

    @Override
    public Object get(Object obj) {
        Object value = owner.get(obj);
        return value == null? null : composite.get(value);
    }

    @Override
    public void set(Object obj, Object newValue) {
        Object field = owner.get(obj);

        if (field == null) {
            field = Reflections.createInstance(fieldType);
            owner.set(obj, field);
        }

        composite.set(field, newValue);
    }
}
