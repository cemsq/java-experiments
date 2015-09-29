package com.cgm.storm.utils.fieldhandler.handlers;

import com.cgm.storm.utils.fieldhandler.FieldHandler;
import com.cgm.storm.utils.fieldhandler.accessor.FieldAccessor;
import com.cgm.storm.utils.reflection.Reflections;

/**
 *
 */
public class CompositeFieldHandler extends SimpleFieldHandler implements FieldHandler {

    protected FieldHandler composite;

    CompositeFieldHandler(FieldAccessor accessor, FieldHandler composite) {
        super(accessor);
        this.composite = composite;
    }

    @Override
    public Object get(Object obj) {
        Object value = super.get(obj);
        return value == null? null : composite.get(value);
    }

    @Override
    public void set(Object obj, Object newValue) {
        Object field = super.get(obj);

        if (field == null) {
            field = Reflections.createInstance(accessor.getFieldType());
            super.set(obj, field);
        }

        composite.set(field, newValue);
    }
}
