package fieldhandler;

import reflection.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ArrayFieldHandler implements FieldHandler {

    private Class fieldType;
    private FieldHandler owner;
    private FieldHandler composite;

    ArrayFieldHandler(Class clazz, String field, String composite) {
        Field f = Reflections.getField(clazz, field);
        fieldType = Reflections.getType(f);

        this.owner = FieldHandlers.create(clazz, field);
        this.composite = FieldHandlers.create(fieldType, composite);
    }

    @Override
    public Object get(Object obj) {
        List ret = null;
        List list = (List)owner.get(obj);

        if (list != null) {
            ret = new ArrayList();
            for (Object element : list) {
                ret.add(composite.get(element));
            }
        }

        return ret;
    }

    @Override
    public void set(Object obj, Object value) {
        List list = new ArrayList();

        for (Object element : (List)value) {
            Object newObject = Reflections.createInstance(fieldType);
            composite.set(newObject, element);
            list.add(newObject);
        }

        owner.set(obj, list);
    }
}
