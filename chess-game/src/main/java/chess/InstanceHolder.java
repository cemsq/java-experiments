package chess;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class InstanceHolder {
    private static Map<Class, Object> instances = new HashMap<>();

    public static <T> T instance(Class<T> clazz) {

        if (!instances.containsKey(clazz)) {
            try {
                Constructor constructor = getEmptyConstructor(clazz);
                constructor.setAccessible(true);

                instances.put(clazz, constructor.newInstance());

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Cannot get new Instance from: " + clazz.getName(), e);
            }
        }

        return (T)instances.get(clazz);
    }

    public static int instancesNumber() {
        return instances.size();
    }

    private static Constructor getEmptyConstructor(Class clazz) {
        for (Constructor constructor : clazz.getConstructors()) {
            if (constructor.getGenericParameterTypes().length == 0) {
                return constructor;
            }
        }

        throw new RuntimeException("no empty Constructor defined");
    }
}
