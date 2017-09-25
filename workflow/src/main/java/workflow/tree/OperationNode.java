package workflow.tree;

public class OperationNode<T> extends Node<T> {

    public OperationNode(T data, Node left, Node right) {
        super(data, left, right);
    }
}
