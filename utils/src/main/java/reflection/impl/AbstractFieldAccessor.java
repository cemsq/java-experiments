package reflection.impl;

import reflection.Casting;
import reflection.FieldAccessor;

public abstract class AbstractFieldAccessor<S> implements FieldAccessor<S> {

    private boolean throwNPE;

    public AbstractFieldAccessor(boolean throwNPE) {
        this.throwNPE = throwNPE;
    }

    @Override
    public <T> T get(S source) {
        checkNull(source);
        T value = null;

        if (source != null) {
            //noinspection unchecked
            value = (T) getInternal(source);

        } else if (throwNPE) {
            throw new NullPointerException("source cannot be null");
        }

        return value;
    }

    @Override
    public <T> T get(S source, Class<T> type) {
        T value = get(source);

        return Casting.cast(value, type);
    }

    @Override
    public final void set(S source, Object value) {
        checkNull(source);
        if (source != null) {
            setInternal(source, value);

        }
    }

    protected void checkNull(Object source) {
        if (source == null && throwNPE) {
            throw new NullPointerException("source cannot be null");
        }
    }

    protected abstract Object getInternal(S source);

    protected abstract void setInternal(S source, Object value);
}
