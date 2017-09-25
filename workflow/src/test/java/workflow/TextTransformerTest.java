package workflow;

import org.testng.Assert;
import org.testng.annotations.Test;
import workflow.base.Condition;
import workflow.base.Criterion;
import workflow.base.Operator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TextTransformerTest {

    TextTransformer transformer = new TextTransformer();

    @Test
    public void test() {
        Condition condition = new Condition(Criterion.Pr, Operator.starsWith, "1", null);

        String str = transformer.get(condition);

        Assert.assertEquals(str, "A = A1");
    }

    @Test
    public void t2() {
        Map<Integer, String> map = new HashMap<>();
        Transformer<String, String> transformer = new Transformer<>();
        transformer.criterion(Criterion.A, () -> "A")
                .criterion(Criterion.P, () -> "P")
                .criterion(Criterion.Pr, () -> "Pr")
                .operator(Operator.equal, (l, r) -> l + " = " + r)
                .operator(Operator.different, (l, r) -> l + " != " + r)
                .operator(Operator.greater, (l, r) -> l + " > " + r)
                .operator(Operator.less, (l, r) -> l + " < " + r);


        Transformer<Function<Item, Object>, Predicate<Item>> t2 = new Transformer<>();
        t2.criterion(Criterion.A, () -> Item::getAssortment)
                .criterion(Criterion.P, () -> Item::getProvider)
                .criterion(Criterion.Pr, () -> Item::getPrice)
                .operator(Operator.equal,
                        (f, v) ->
                                (item) -> f.apply(item).equals(v))
                .operator(Operator.different,
                        (f, v) ->
                                (item) -> !f.apply(item).equals(v))
                .operator(Operator.greater,
                        (f, v) ->
                                (item) -> (int) f.apply(item) > (int) v)
                .operator(Operator.less,
                        (f, v) ->
                                (item) -> (int) f.apply(item) < (int) v);


        Condition condition = new Condition(Criterion.Pr, Operator.greater, "5", null);

        String str = transformer.get(condition);
//        Assert.assertEquals(str, "Pr = 5");

        Predicate<Item> predicate = t2.get(condition);
        Item item = new Item("A1", "P1", 3);
        Assert.assertEquals(predicate.test(item), false);
    }
}
