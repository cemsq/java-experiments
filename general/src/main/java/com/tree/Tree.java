package com.tree;

/**
 *
 */
public interface Tree<K, V> {

    default V get(K key) { return TreeNode.getValue(getNode(key)); };
    TreeNode<K, V> getNode(K key);

//    int size();
//    void clear();
//    default boolean isEmpty() { return size() == 0; }
//    boolean contains(K key);
//    boolean containsValue(V value);
}
