package workflow;

public interface Reducer<T> {

    T reduce(T left, T right);
}
