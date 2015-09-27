package reflection.exception;

/**
 *
 */
public class ConstructorNotFoundException extends ReflectionsException {

    public ConstructorNotFoundException(Class clazz) {
        super("Empty Constructor not found in Class: " + clazz.getName());
    }
}
