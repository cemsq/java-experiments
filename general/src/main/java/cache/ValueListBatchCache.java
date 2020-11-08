package cache;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValueListBatchCache<K, V> extends AbstractBatchCache2<K, List<V>, List<V>> {

    private Function<V, K> keyMapper;

    @Override
    public List<V> get(K key) {

        if (key != null) {
            return getAll(Collections.singletonList(key));
        }
        return Collections.emptyList();
    }

    @Override
    public List<V> getAll(List<K> keys) {
        List<V> result = Lists.newArrayList();
        get(keys, result::addAll, null);
        return result;
    }

    @Override
    protected Map<K, List<V>> batchResultToMap(List<V> result) {
        return result.stream().collect(Collectors.groupingBy(keyMapper, Collectors.toList()));
    }
}
