package reflection.impl;

import com.google.common.base.Preconditions;
import reflection.FieldAccessor;
import reflection.Reflections;

import java.lang.reflect.Field;

public class FieldAccessorImpl<S> extends AbstractFieldAccessor<S> implements FieldAccessor<S> {

    private Field field;

    public FieldAccessorImpl(Field field, boolean throwNPE) {
        super(throwNPE);
        Preconditions.checkNotNull(field, "field cannot be null");
        this.field = field;
    }

    @Override
    protected Object getInternal(S source) {
        return Reflections.getFieldValue(field, source);
    }

    @Override
    protected void setInternal(S source, Object value) {
        Reflections.setFieldValue(field, source, value);
    }
}
