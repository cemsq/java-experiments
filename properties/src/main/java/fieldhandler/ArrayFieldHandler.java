package fieldhandler;

import reflection.Reflections;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ArrayFieldHandler extends CompositeFieldHandler implements FieldHandler {

    public ArrayFieldHandler(FlatFieldHandler owner, FieldHandler composite) {
        super(owner, composite);
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
            Object newObject = Reflections.createInstance(owner.getFieldType());
            composite.set(newObject, element);
            list.add(newObject);
        }

        owner.set(obj, list);
    }
}
