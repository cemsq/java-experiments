package exp.performance;

import org.testng.annotations.Test;

import java.util.function.Predicate;

/**
 *
 */
public class MethodCalls {

    private static final Integer MAX_ITERATIONS = 100_000_000;

    @Test
    public void nested() {
        test(this::isValidNested);
    }

    @Test
    public void flat() {
        test(s -> s != null && !s.isEmpty());
    }

    private void test(Predicate<String> filter) {
        int x = 0;
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            generate();
//            if (filter.test(generate())) {
//                x++;
//            }
        }
    }

    private static String generate() {
        return String.valueOf(Math.random() * MAX_ITERATIONS);
    }

    private boolean isValidNested(String value) {
        return !isNullOrEmptyNested(value);
    }

    private boolean isNullOrEmptyNested(String value) {
        return isNull(value) || isEmptyNested(value);
    }

    private boolean isEmptyNested(String value) {
        return !isNull(value) && value.isEmpty();
    }

    private boolean isNull(String value) {
        return value == null;
    }
}
