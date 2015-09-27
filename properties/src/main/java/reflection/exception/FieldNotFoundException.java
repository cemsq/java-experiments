package reflection.exception;

/**
 *
 */
public class FieldNotFoundException extends ReflectionsException {
    public FieldNotFoundException(String className, String field) {
        super("In Class: " + className + ". FieldName: " + field);
    }
}
