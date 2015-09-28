package fieldhandler.accessor;

import fieldhandler.AccessorType;
import reflection.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 */
public abstract class FieldAccessor<T> {
    public static final AccessorType DEFAULT_TYPE = AccessorType.GETTER_SETTER;

    private Class fieldType;
    private Field field;
    private boolean isArray;

    public FieldAccessor(Class<T> clazz, String field) {
        this.field = Reflections.getField(clazz, field);
        this.fieldType = Reflections.getType(this.field);
        this.isArray = this.field.getType().equals(List.class);
    }

    public abstract Object get(T obj);

    public abstract void set(T obj , Object value);

    public Class getType() {
        return fieldType;
    }

    public boolean isArray() {
        return isArray;
    }

    protected Field getField() {
        return field;
    }

    public static<K> FieldAccessor<K> factory(Class<K> clazz, String field) {
        return factory(clazz, field, DEFAULT_TYPE);
    }

    public static<K> FieldAccessor<K> factory(Class<K> clazz, String field, AccessorType type) {
        switch (type) {
            case DIRECT_FIELD: return new DirectField<>(clazz, field);

            case GETTER_SETTER: return new GetterSetter<>(clazz, field);

            default:
                throw new RuntimeException("Unsupported AccessorType: " + type.name());
        }
    }

    // ***********************************************
    // * DirectField Accessor
    // ***********************************************
    static class DirectField<D> extends FieldAccessor<D> {

        private DirectField(Class<D> clazz, String field) {
            super(clazz, field);
        }

        @Override
        public Object get(D obj) {
            return Reflections.getFieldValue(getField(), obj);
        }

        @Override
        public void set(D obj, Object value) {
            Reflections.setFieldValue(getField(), obj, value);
        }
    }

    // ***********************************************
    // * GetterSetter Accessor
    // ***********************************************
    static class GetterSetter<F> extends FieldAccessor<F> {

        private Method getter;
        private Method setter;

        private GetterSetter(Class<F> clazz, String fieldName) {
            super(clazz, fieldName);
            getter = Reflections.getter(clazz, fieldName);
            setter = Reflections.setter(clazz, fieldName);
        }

        @Override
        public Object get(F obj) {
            return Reflections.invoke(getter, obj);
        }

        @Override
        public void set(F obj, Object value) {
            Reflections.invoke(setter, obj, value);
        }
    }
}
