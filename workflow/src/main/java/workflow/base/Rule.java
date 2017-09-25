package workflow.base;

import com.google.common.cache.CacheBuilder;

import java.util.List;

public class Rule {

    List<Condition> conditions;

    public Rule(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Condition> getConditions() {

        return conditions;
    }
}
