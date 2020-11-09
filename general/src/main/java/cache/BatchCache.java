package cache;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BatchCache<K, V> extends AbstractBatchCache2<K, V, List<V>> {

    private Function<V, K> keyMapper;

    @Override
    public V get(K key) {
        if (key != null) {
            List<V> values = getAll(Collections.singletonList(key));
            if  (values.size() > 0) {
                return values.get(0);
            }
        }
        return null;
    }

    @Override
    public List<V> getAll(List<K> keys) {
        List<V> result = Lists.newArrayList();
        get(keys, result::add, null);
        return result;
    }

    @Override
    protected Map<K, V> batchResultToMap(List<V> result) {
        return result.stream().collect(Collectors.toMap(keyMapper, Function.identity()));
    }

}
