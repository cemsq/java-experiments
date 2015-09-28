package fieldhandler.handlers;

import fieldhandler.FieldHandler;
import reflection.Reflections;

/**
 *
 */
public class CompositeFieldHandler implements FieldHandler {

    protected SimpleFieldHandler owner;
    protected FieldHandler composite;

    public CompositeFieldHandler(SimpleFieldHandler owner, FieldHandler composite) {
        this.owner = owner;
        this.composite = composite;
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
            field = Reflections.createInstance(owner.getFieldType());
            owner.set(obj, field);
        }

        composite.set(field, newValue);
    }
}
