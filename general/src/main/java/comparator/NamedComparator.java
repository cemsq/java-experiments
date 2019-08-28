package comparator;

import com.google.common.base.Preconditions;

import java.util.Comparator;

public class NamedComparator<T> implements Comparator<T> {

    private String name;
    private Comparator<T> delegate;

    public NamedComparator(String name, Comparator<T> delegate) {
        this.name = name;
        this.delegate = Preconditions.checkNotNull(delegate, "delegate");
    }

    public String name() {
        return name;
    }

    @Override
    public int compare(T o1, T o2) {
        return delegate.compare(o1, o2);
    }
}
