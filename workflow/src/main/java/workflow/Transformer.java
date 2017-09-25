package workflow;

import com.google.common.collect.Maps;
import workflow.base.Condition;
import workflow.base.Criterion;
import workflow.base.Operator;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Transformer<T, O> {

    private Map<Criterion, Supplier<T>> fieldSupplier = Maps.newHashMap();
    private Map<Operator, BiFunction<T, Object, O>> operatorMap = Maps.newHashMap();
    private CriterionValue mapper = new CriterionValue();

    public Transformer() {

    }

    public Transformer<T, O> criterion(Criterion criterion, Supplier<T> fieldSupplier) {
        this.fieldSupplier.put(criterion, fieldSupplier);

        return this;
    }

    public Transformer<T, O> operator(Operator operator, BiFunction<T, Object, O> transformer) {
        this.operatorMap.put(operator, transformer);

        return this;
    }

    public O get(Condition condition) {
        Supplier<T> supplier = fieldSupplier.get(condition.getCriterion());
        if (supplier == null) {
            throw throwNotSupported("Criterion", condition.getCriterion());
        }

        BiFunction<T, Object, O> operator = operatorMap.get(condition.getOperator());
        if (operator == null) {
            throw throwNotSupported("Operator", condition.getOperator());
        }

        T field = supplier.get();
        Object value = mapper.get(condition.getCriterion(), condition.getValue());

        return operator.apply(field, value);
    }

    protected IllegalArgumentException throwNotSupported(String type, Object value) {
        String message = String.format("%s '%s' not supported in %s", type, value, this.getClass().getSimpleName());

        return new IllegalArgumentException(message);
    }
}
