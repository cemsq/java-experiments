package cem.rules;

import java.util.List;

public class ProviderRules implements RuleChecker<String> {

    RulesContainer<String> rules = new RulesContainer<>();

    public ProviderRules() {
        rules.allow("S", "P");
        rules.allow("S", "G");
        rules.allow("P", "C");
        rules.allow("G", "C");
        rules.allow("C", "A");
    }

    @Override
    public RuleResult check(List<String> parentList, List<String> childList) {
        return rules.check(parentList, childList);
    }
}
