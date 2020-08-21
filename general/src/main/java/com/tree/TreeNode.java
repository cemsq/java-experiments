package com.tree;

/**
 *
 */
public interface TreeNode<K, V> {

    static <K, V> V getValue(TreeNode<K, V> node) { return node != null? node.value() : null; };

    K key();
    V value();
    default V parent() { return getValue(parentNode()); }

    TreeNode<K, V> parentNode();
}
