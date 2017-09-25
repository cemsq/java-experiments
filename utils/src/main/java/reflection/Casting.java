package reflection;

import com.google.common.base.Preconditions;

import java.lang.reflect.ParameterizedType;

public class Casting {

    public static <T> T cast(Object value) {
        T val = null;

        if (value != null) {
            //noinspection unchecked
//            Class<T> type = (Class<T>) ((ParameterizedType) value.getClass()
//                    .getGenericSuperclass())
//                    .getActualTypeArguments()[0];
//
//            val = cast(value, type);

            //noinspection unchecked
            val = (T)value;
        }

        return val;
    }

    public static <T> T cast(Object value, Class<T> type) {
        Preconditions.checkNotNull(type, "type cannot be null");

        return type.cast(value);
    }
}
