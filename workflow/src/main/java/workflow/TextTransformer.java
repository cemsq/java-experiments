package workflow;

import workflow.base.Criterion;
import workflow.base.Operator;

import java.util.function.BiFunction;

public class TextTransformer extends Transformer<String, String> {

    public TextTransformer() {
        criterion(Criterion.A, () -> "A");
        criterion(Criterion.P, () -> "P");
        criterion(Criterion.Pr, () -> "Pr");

        operator(Operator.equal, f("="));
        operator(Operator.different, f("!="));
        operator(Operator.greater, f(">"));
        operator(Operator.less, f("<"));
        operator(Operator.starsWith, f("starts with"));
    }

    private BiFunction<String, Object, String> f(String op) {
        return (l, r) -> l + " " + op + " " + r;
    }
}
