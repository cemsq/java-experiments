package datastore;

import com.google.common.base.Preconditions;

import java.util.*;

/**
 * Created by cesar on 21/03/2015.
 */
public class PseudoDataStore<V> {

    // to sort by insertion position (index)
    private List<V> list = new ArrayList<>();

    // to avoid duplicated references
    // TODO: find a better way, another DataStructure
    private Map<V,Integer> byReference = new HashMap<>();


    public PseudoDataStore() {

    }

    public PseudoDataStore<V> add(V value) {
        if (!byReference.containsKey(value)) {
            byReference.put(value ,null);
            list.add(value);
        }

        return this;
    }

    public void set(int index, V value) {
        Preconditions.checkArgument(index < size(), "There are no index like: " + index);

        V old = getByIndex(index);
        byReference.remove(old);

        list.set(index, value);
        byReference.put(value, null);
    }

    public V getByIndex(int index) {
        return list.get(index);
    }

    public int indexOf(V value) {
        return list.indexOf(value);
    }

    public boolean remove(V value) {
        boolean removed = list.remove(value);

        if (removed) {
            byReference.remove(value);
        }

        return removed;
    }

    public boolean removeByIndex(int index) {
        V value = getByIndex(index);
        return remove(value);
    }

    // TODO: delete me. only for test phase
    public List<V> getList() {
        return list;
    }

    // TODO: delete me. only for test phase
    public Map<V, Integer> getByReference() {
        return byReference;
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int index = 0;
        for (V v : list) {
            if (!first) {
                sb.append("\n");
            }
            first = false;
            sb.append("[");
            sb.append(index);
            sb.append("]: ");
            sb.append(v);
            index++;
        }

        return sb.toString();
    }
}
