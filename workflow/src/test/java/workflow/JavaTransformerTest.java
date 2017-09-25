package workflow;

import org.testng.Assert;
import org.testng.annotations.Test;
import workflow.base.Condition;
import workflow.base.Criterion;
import workflow.base.Operator;

import java.util.function.Predicate;

public class JavaTransformerTest {
    JavaTransformer transformer = new JavaTransformer();

    @Test
    public void test() {
        Condition c1 = new Condition(Criterion.A, Operator.equal, "A2", null);
        Condition c2 = new Condition(Criterion.Pr, Operator.less, "5", null);

        Predicate<Item> predicate = transformer.get(c1).or(transformer.get(c2));

        Item item = new Item("A1", "P1", 3);
        boolean test = predicate.test(item);

        Assert.assertEquals(test, true);
    }
}
