package properties.exception;

/**
 *
 */
public class ClassHelperException extends RuntimeException{
    public ClassHelperException(String message) {
        super(message);
    }

    public ClassHelperException(String message, Throwable cause) {
        super(message, cause);
    }
}
