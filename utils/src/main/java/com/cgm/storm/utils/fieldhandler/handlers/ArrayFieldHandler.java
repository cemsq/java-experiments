package com.cgm.storm.utils.fieldhandler.handlers;

import com.cgm.storm.utils.fieldhandler.FieldHandler;
import com.cgm.storm.utils.fieldhandler.accessor.FieldAccessor;
import com.cgm.storm.utils.reflection.Reflections;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ArrayFieldHandler extends CompositeFieldHandler implements FieldHandler {

    ArrayFieldHandler(FieldAccessor accessor, FieldHandler composite) {
        super(accessor, composite);
    }

    @Override
    public Object get(Object obj) {
        List ret = null;
        List list = (List)accessor.get(obj);

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
        List list = (List)accessor.get(obj);

        if (list == null) {
            list = new ArrayList();
        }

        int index = 0;
        for (Object newValue : (List)value) {
            Object element;
            // copy 1 to 1
            if (index < list.size()) {
                element = list.get(index);
            } else {
                // new element in array
                element = Reflections.createInstance(accessor.getFieldType());
                list.add(element);
            }

            composite.set(element, newValue);
            index++;
        }

        accessor.set(obj, list);
    }
}
