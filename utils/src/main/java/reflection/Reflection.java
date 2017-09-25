package reflection;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import reflection.impl.FieldAccessorImpl;

import java.lang.reflect.Field;

public class Reflection {

    public static<S> FieldAccessor<S> fieldAccessor(Class<S> clazz, String name, FieldAccessMode mode) {
        Preconditions.checkNotNull(clazz, "class cannot be null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "name cannot be null nor empty");
        Preconditions.checkNotNull(mode, "mode cannot be null");

        Field field = Reflections.getField(clazz, name);

        switch (mode) {
            case Field:
                return new FieldAccessorImpl<>(field, false);

            default:
                throw new RuntimeException("FieldAccessMode not supported " + mode);
        }
    }
}
