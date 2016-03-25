package cem.rules;

import java.util.List;

public interface RuleChecker<T> {

    RuleResult check(List<T> parentList, List<T> childList);

}
