package workflow.tree;

import workflow.base.Condition;
import workflow.base.Operator;
import workflow.base.Rule;

import java.util.Stack;

public class Tree<T> {
    private Node<T> root;

    public static Tree<Condition> create(Rule rule) {
        int open = 0;
        int close = 0;

        Stack<ConditionNode<Condition>> stack = new Stack<>();
        Operator operator = null;

        for (Condition condition : rule.getConditions()) {
            open += condition.getOpen();

            stack.push(new ConditionNode<>(condition, null, null));

            close = condition.getClose();
            while (close > 0) {
                close--;
                open--;

                if (open > 0) {
                    if (stack.size() == 2 && operator != null) {
                        ConditionNode<Condition> right = stack.pop();
                        ConditionNode<Condition> left = stack.pop();
                        ConditionNode<Condition> op = new ConditionNode<>(left.getData(), left, right);

                        stack.push(op);

                    }
                }
            }
            operator = condition.getOperator();


        }

        return null;
    }
}

