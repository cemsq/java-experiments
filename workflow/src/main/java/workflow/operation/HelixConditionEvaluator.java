package workflow.operation;

import workflow.base.Condition;
import workflow.base.HelixCondition;

import java.util.function.Supplier;

public class HelixConditionEvaluator implements ConditionEvaluator<HelixCondition, String> {


    @Override
    public HelixCondition parse(Condition condition, Supplier<String> fieldExtractor) {
        String field = fieldExtractor.get();
        String value = condition.getValue();

        switch (condition.getOperator()) {
            case equal: return HelixCondition.equal(field, value);

            case different:

            default:
                throw new RuntimeException("not supported: " + condition.getOperator());
        }
    }
}
