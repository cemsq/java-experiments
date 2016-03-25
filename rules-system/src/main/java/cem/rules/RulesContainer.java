package cem.rules;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RulesContainer<T> implements RuleChecker<T> {

    private Set<Rule<T>> rules = Sets.newHashSet();

    public RulesContainer() {
    }

    public RulesContainer<T> allow(T parent, T child) {
        return add(parent, child, Action.ALLOW, "");
    }

    public RulesContainer<T> allow(T parent, T child, String errorMessage) {
        return add(parent, child, Action.ALLOW, errorMessage);
    }

    public RulesContainer<T> add(T parent, T child, Action action, String errorMessage) {
        return add(Rule.create(parent, child, action, errorMessage));
    }

    public RulesContainer<T> add(Rule<T> rule) {
        rules.add(rule);

        return this;
    }

    public List<Rule<T>> getRules() {
        return rules
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public RuleResult check(List<T> parentList, List<T> childList) {

        boolean allow = false;
        for (Rule<T> rule : getRules()) {
            RuleResult result = rule.check(parentList, childList);

            if (result.isAllowed()) {
                allow = true;
            } else if (result.isDenied()) {
                return result;
            }
        }

        if (allow) {
            return RuleResult.create(Action.ALLOW, "");
        }

        return RuleResult.create(Action.DENY, "no rules matched");
    }
}
