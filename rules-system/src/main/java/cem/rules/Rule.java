package cem.rules;

import com.google.common.base.Preconditions;

import java.util.List;

public class Rule<T> implements RuleChecker<T> {
    private T parent;
    private T child;
    private Action action;
    private String errorMessage;

    public static<T> Rule<T> create(T parent, T child, Action action) {
        return new Rule<>(parent, child, action, "");
    }

    public static<T> Rule<T> create(T parent, T child, Action action, String errorMessage) {
        return new Rule<>(parent, child, action, errorMessage);
    }

    private Rule(T parent, T child, Action action, String errorMessage) {
        this.parent = parent;
        this.child = child;
        this.action = action;
        this.errorMessage = errorMessage;
    }

    @Override
    public RuleResult check(List<T> parentList, List<T> childList) {
        Preconditions.checkNotNull(parentList, "null parent list");
        Preconditions.checkNotNull(childList, "null child list");

        if (parentList.contains(parent) && childList.contains(child)) {
            return RuleResult.create(action, errorMessage);
        }

        return RuleResult.create(Action.UNDEFINED, "");
    }
}
