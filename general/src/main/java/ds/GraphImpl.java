package ds;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

public class GraphImpl<K extends Comparable<? super K>, V> implements Graph<K, V> {

    private Map<K, Node> graph = Maps.newHashMap();
    private Function<V, K> keyExtractor;

    public GraphImpl(Function<V, K> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    public class Node {
        private V info;
        private Map<K, Link> links;

        public Node(V info) {
            this.info = info;
            this.links = Maps.newHashMap();
        }

        public void link(Node target, double weight) {
            if (target == null || emptyNode.equals(target)) {
                throw new UnsupportedOperationException("Cannot link to empty node");
            }

            K key = keyExtractor.apply(target.info);
            Link link = links.get(key);
            if (link == null) {
                link = new Link(target, weight);
                links.put(key, link);
            } else {
                link.target = target;
                link.weight = weight;
            }
        }

        public boolean isConnected(K target) {
            return links.get(target) != null;
        }
    }

    private final Node emptyNode = new Node(null) {
        @Override
        public void link(Node target, double weight) {
            throw new UnsupportedOperationException("Cannot link from empty node");
        }

        @Override
        public boolean isConnected(K target) {
            return false;
        }
    };


    public class Link {
        private Node target;
        private double weight;

        public Link(Node target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    @Override
    public boolean isConnected(K from, K to) {
        return findKeyOrEmpty(from).isConnected(to);
    }

    @Override
    public void link(K from, K to) {
        Node fNode = graph.get(from);
        Node tNode = graph.get(to);

        fNode.link(tNode, 0);
    }

    @Override
    public void linkNodes(V from, V to) {
        Node start = findOrCreateNode(from);
        Node target = findOrCreateNode(to);

        start.link(target, 0);
    }

    private Node findOrCreateNode(V value) {
        K key = keyExtractor.apply(value);
        Node node = find(value);
        if (node == null) {
            node = new Node(value);
            graph.put(key, node);
        }

        return node;
    }

    private Node findKeyOrEmpty(K key) {
        Node node = graph.get(key);
        if (node == null) {
            node = emptyNode;
        }

        return node;
    }

    private Node find(V value) {
        K key = keyExtractor.apply(value);
        return graph.get(key);
    }
}
