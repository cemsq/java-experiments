package cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @param <K> Key
 * @param <V> Value
 * @param <B> Batch result
 */
public abstract class AbstractBatchCache<K, V, B> implements Cache<K, V> {

    protected Cache<K, V> cache;

    public abstract V get(K key);
    public abstract B getAll(List<K> keys);
    protected abstract Map<K, V> batchResultToMap(B result);

    @Override
    public void put(K key, V value) {
        if (key != null && value != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void putAll(Map<K, V> source) {
        putAll(source, null);
    }

    @Override
    public void putAll(Map<K, V> source, Consumer<V> consumer) {
        if (source != null) {
            for (Map.Entry<K, V> e : source.entrySet()) {
                V value = e.getValue();
                put(e.getKey(), value);
                if (consumer != null) {
                    consumer.accept(value);
                }
            }
        }
    }

    protected void load(List<K> keys, Function<List<K>, B> loader, Consumer<V> consumer) {

        List<K> absent = Lists.newArrayList();
        Set<K> visited = Sets.newHashSet();

        for (K key : keys) {

            if (key == null || visited.contains(key)) {
                continue;
            }

            visited.add(key);

            V value = cache.get(key);
            if (value != null) {
                consumer.accept(value);
            } else {
                absent.add(key);
            }
        }

        if (!absent.isEmpty()) {
            B result = loader.apply(absent);
            Map<K, V> map = batchResultToMap(result);
            cache.putAll(map, consumer);
        }
    }
}
