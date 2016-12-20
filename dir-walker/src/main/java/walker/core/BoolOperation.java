package walker.core;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.function.Predicate;

public enum BoolOperation {
    AND(true, false, true),
    OR(false, true, false);

    private boolean negate;
    private boolean returnWhenFound;
    private boolean defaultReturnValue;

    BoolOperation(boolean negate, boolean returnWhenFound, boolean defaultReturnValue) {
        this.negate = negate;
        this.returnWhenFound = returnWhenFound;
        this.defaultReturnValue = defaultReturnValue;
    }

    public<T> boolean check(List<T> list, Predicate<T> predicate) {
        Preconditions.checkNotNull(list, "list must be provided");
        Preconditions.checkNotNull(predicate, "predicate must be provided");

        if (list.isEmpty()) {
            return false;
        }

        for (T element : list) {
            if (returnNow(predicate, element)) {
                return returnWhenFound;
            }
        }

        return defaultReturnValue;
    }

    <T> boolean returnNow(Predicate<T> predicate, T element) {
        return (element != null && predicate.test(element)) ^ negate;
    }
}
