package cem.rules;


import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RuleCheckerTest {

    @Test
    public void shouldAllow() {
        Rule<String> rule = Rule.create("S", "P", Action.ALLOW);

        RuleResult result = rule.check(Lists.newArrayList("S"), Lists.newArrayList("P"));
        Assert.assertEquals(result.getAction(), Action.ALLOW);
    }

    @Test
    public void shouldUndefined() {
        Rule<String> rule = Rule.create("S", "P", Action.ALLOW);

        RuleResult result = rule.check(Lists.newArrayList("S"), Lists.newArrayList("X"));
        Assert.assertEquals(result.getAction(), Action.UNDEFINED);
    }
}
