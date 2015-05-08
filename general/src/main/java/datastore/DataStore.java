package datastore;

/**
 * Created by cesar on 21/03/2015.
 */
public interface DataStore<V> {

    public DataStore<V> add(V value);

    public V getRow(int index);

    public void setRow(int index, V newValue);

    public int size();
}
