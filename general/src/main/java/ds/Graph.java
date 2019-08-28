package ds;

public interface Graph<K extends Comparable<? super K>, V> {

    boolean isConnected(K from, K to);

    void link(K from, K to);

    void linkNodes(V from, V to);
}
