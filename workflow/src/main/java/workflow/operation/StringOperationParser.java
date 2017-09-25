package workflow.operation;

import workflow.base.Condition;

import java.util.function.Supplier;

public class StringOperationParser implements ConditionEvaluator<String, String> {

    @Override
    public String parse(Condition condition, Supplier<String> fieldExtractor) {
        String field = fieldExtractor.get();
        String value = condition.getValue();

        switch (condition.getOperator()) {
            case equal: return field + " = " + value;

            case different: return field + " != " + value;

            default:
                throw new RuntimeException("not supported: " + condition.getOperator());
        }
    }
}
