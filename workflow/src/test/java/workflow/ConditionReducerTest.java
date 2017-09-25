package workflow;

import org.testng.Assert;
import org.testng.annotations.Test;
import workflow.base.BoolOperation;
import workflow.base.Condition;
import workflow.base.Criterion;
import workflow.base.Operator;
import workflow.operation.StringOperationParser;

public class ConditionReducerTest {


    @Test
    public void test( ) {
        StringOperationParser parser = new StringOperationParser();

        Condition condition = new Condition(Criterion.A, Operator.equal, "3", BoolOperation.AND);

        String value = parser.parse(condition, () -> "a.li");
        Assert.assertEquals(value, "al.lsd");
    }

    public void reducer() {
        new ConditionReducer<String>(
                (l, r) -> l + " & " + r,
                (l, r) -> l + " | " + r);
    }
}
