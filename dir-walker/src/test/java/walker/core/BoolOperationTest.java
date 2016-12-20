package walker.core;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import walker.core.BoolOperation;

import java.util.List;
import java.util.function.Predicate;

public class BoolOperationTest {

    @Test(dataProvider = "provider")
    public void test(BoolOperation op, List<String> list, Predicate<String> predicate, boolean expected) {
        boolean check = op.check(list, predicate);

        Assert.assertEquals(check, expected, op.name() + " operation failed");
    }

    @DataProvider
    public Object[][] provider() {
        List<String> list = Lists.newArrayList("1", "2", "3", "4");

        return new Object[][] {
                {BoolOperation.OR, list, equals("3"), true},
                {BoolOperation.OR, list, equals("5"), false},
                {BoolOperation.AND, list, less("5"), true},
                {BoolOperation.AND, list, less("0"), false},
        };
    }

    private static Predicate<String> equals(String value) {
        return value::equals;
    }

    private static Predicate<String> less(String value) {
        return (str) -> value.compareTo(str) > 0;
    }
}
