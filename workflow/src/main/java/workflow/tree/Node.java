package workflow.tree;

public class Node<T> {

    private T data;
    private Node left;
    private Node right;

    public Node(T data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }
}
