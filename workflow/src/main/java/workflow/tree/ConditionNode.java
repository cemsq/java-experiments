package workflow.tree;

public class ConditionNode<T> extends Node<T> {

    public ConditionNode(T data, Node left, Node right) {
        super(data, left, right);
    }
}
