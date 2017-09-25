package workflow;

import workflow.base.Condition;
import workflow.base.Criterion;
import workflow.base.Operator;

import java.util.function.Function;
import java.util.function.Predicate;

public class JavaTransformer extends Transformer<Function<Item, Object>, Predicate<Item>> {

    public JavaTransformer() {

        criterion(Criterion.A, () -> Item::getAssortment);
        criterion(Criterion.P, () -> Item::getProvider);
        criterion(Criterion.Pr, () -> Item::getPrice);

        operator(Operator.equal,
                (f, v) ->
                        (item) -> f.apply(item).equals(v));

        operator(Operator.different,
                (f, v) ->
                        (item) -> !f.apply(item).equals(v));

        operator(Operator.greater,
                (f, v) ->
                        (item) -> (int)f.apply(item) > (int)v);

        operator(Operator.less,
                (f, v) ->
                        (item) -> (int)f.apply(item) < (int)v);
    }

    @Override
    public Predicate<Item> get(Condition condition) {
        switch (condition.getCriterion()) {
            case A:
        }

        return super.get(condition);
    }
}
