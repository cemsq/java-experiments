package com.cgm.storm.utils.fieldhandler.handlers;


import com.cgm.storm.utils.fieldhandler.FieldHandler;
import com.cgm.storm.utils.fieldhandler.accessor.FieldAccessor;

/**
 *
 */
public class SimpleFieldHandler implements FieldHandler {

    protected FieldAccessor accessor;

    SimpleFieldHandler(FieldAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public Object get(Object obj) {
        return accessor.get(obj);
    }

    @Override
    public void set(Object obj, Object value) {
        accessor.set(obj, value);
    }
}
