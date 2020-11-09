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
public abstract class AbstractBatchCache2<K, V, B> {

    protected Cache<K, V> cache;

    public abstract V get(K key);
    public abstract B getAll(List<K> keys);
    protected abstract Map<K, V> batchResultToMap(B result);

    public void get(List<K> keys, Consumer<V> consumer, Function<List<K>, B> loader) {
        get(keys, consumer, loader, this::batchResultToMap);
    }

    public void get(List<K> keys, Consumer<V> consumer, Function<List<K>, B> loader, Function<B, Map<K, V>> resultMapper) {

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

        if (!absent.isEmpty() && loader != null && resultMapper != null) {
            B result = loader.apply(absent);
            Map<K, V> map = resultMapper.apply(result);
            cache.putAll(map, consumer);
        }
    }
}
