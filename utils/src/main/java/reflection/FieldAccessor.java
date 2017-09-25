package reflection;

public interface FieldAccessor<S> {

    <T> T get(S source);

    <T> T get(S source, Class<T> type);

    void set(S source, Object value);
}
