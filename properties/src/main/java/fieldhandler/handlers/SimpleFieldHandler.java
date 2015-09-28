package fieldhandler.handlers;

import fieldhandler.FieldHandler;
import fieldhandler.accessor.FieldAccessor;

/**
 *
 */
public class SimpleFieldHandler implements FieldHandler {

    private FieldAccessor accessor;

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

    public Class getFieldType() {
        return accessor.getType();
    }

    public boolean isArray() {
        return accessor.isArray();
    }
}
