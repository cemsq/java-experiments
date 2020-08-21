package com.tree;

import java.util.Map;

/**
 *
 */
public class TreeImpl<K, V> implements Tree<K, V> {

    private Map<K, Node> table;

    @Override
    public TreeNode<K, V> getNode(K key) {
        return null;
    }

    private class Node implements TreeNode<K, V> {
        
        @Override
        public K key() {
            return null;
        }

        @Override
        public V value() {
            return null;
        }

        @Override
        public TreeNode<K, V> parentNode() {
            return null;
        }
    }
}
