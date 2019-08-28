package comparator;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;

public class ComparatorBuilder<T> {
    public enum NullStrategy {FIRST, LAST, FAIL};

    private NullStrategy onNull = NullStrategy.FIRST;
    private Set<Comparator<T>> delegate = Sets.newHashSet();

    public static<T> ComparatorBuilder<T> start(Class<T> type) {
        Preconditions.checkNotNull(type, "type");
        return new ComparatorBuilder<>();
    }

    public ComparatorBuilder<T> onNull(NullStrategy onNull) {
        this.onNull = Preconditions.checkNotNull(onNull, "onNull");
        return this;
    }

    public <U extends Comparable<? super U>> ComparatorBuilder<T> then(Function<? super T, ? extends U> keyExtractor) {
        Preconditions.checkNotNull(keyExtractor, "keyExtractor");

        delegate.add(Comparator.comparing(keyExtractor));

        return this;
    }

    public Comparator<T> build() {
        Comparator<T> comparator = (l, r) -> {
//            if (l == r) {
//                return 0;
//            }

            return l != null && r != null? 0 : 1;
        };

        for (Comparator<T> c : delegate) {
            Comparator<T> nullsWrapped = wrap(c);
            comparator = comparator.thenComparing(nullsWrapped);
        }

        return comparator;
    }

    private <U extends Comparable<? super U>> Comparator<T> create(Function<? super T, ? extends U> keyExtractor) {
        Comparator<U> nullHandler;

        Comparator<U> naturalOrder = Comparator.naturalOrder();
        switch (onNull) {

            case FIRST:
                nullHandler = Comparator.nullsFirst(naturalOrder);
                break;

            case LAST:
                nullHandler = Comparator.nullsLast(naturalOrder);
                break;

            case FAIL:
                nullHandler = (left, right) -> {
                    Preconditions.checkNotNull(left, "left");
                    Preconditions.checkNotNull(right, "right");

                    return naturalOrder.compare(left, right);
                };
                break;

            default: throw new UnsupportedOperationException(onNull.name());
        }

        return Comparator.comparing(keyExtractor, nullHandler);
    }

    private Comparator<T> wrap(Comparator<T> delegate) {
        switch (onNull) {

            case FIRST:
                return Comparator.nullsFirst(delegate);

            case LAST:
                return Comparator.nullsLast(delegate);

            case FAIL:
                return (left, right) -> {
                    Preconditions.checkNotNull(left, "left");
                    Preconditions.checkNotNull(right, "right");

                    return delegate.compare(left, right);
                };

            default: throw new UnsupportedOperationException(onNull.name());
        }
    }
}
