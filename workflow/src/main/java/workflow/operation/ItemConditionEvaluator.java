package workflow.operation;

import workflow.Item;
import workflow.base.Condition;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ItemConditionEvaluator<T, V> implements ConditionEvaluator<Predicate<Item>, V> {

    @Override
    public Predicate<Item> parse(Condition condition, Supplier<V> fieldExtractor) {
        V field = fieldExtractor.get();
        String value = condition.getValue();

        switch (condition.getOperator()) {
            case equal:

            case different:

            default:
                throw new RuntimeException("not supported: " + condition.getOperator());
        }
    }
}
