package cache;

import java.util.Map;
import java.util.function.Consumer;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    void putAll(Map<K, V> source);

    void putAll(Map<K, V> source, Consumer<V> consumer);
}
