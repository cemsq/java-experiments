package workflow.operation;

import workflow.base.Condition;

import java.util.function.Supplier;

public interface ConditionEvaluator<R, V> {

    R parse(Condition condition, Supplier<V> fieldExtractor);
}
