package cem.rules.api;

import com.google.common.collect.Sets;

import java.util.Set;

public class RelationContainer<OBJECT> implements RelationChecker<OBJECT> {

    private Set<Relation<OBJECT>> relations = Sets.newHashSet();

    public RelationContainer() {
    }

    public RelationContainer<OBJECT> add(RelationCondition<OBJECT> parent,
                                         RelationCondition<OBJECT> child,
                                         RelationResult result) {

        return add(new Relation<>(parent, child, result));
    }

    public RelationContainer<OBJECT> add(Relation<OBJECT> rule) {
        relations.add(rule);

        return this;
    }

    @Override
    public RelationResult check(OBJECT left, OBJECT right) {
        boolean allowed = false;

        for (Relation<OBJECT> relation : relations) {
            RelationResult result = relation.check(left, right);

            if (result.isDenied()) {
                return result;
            } else if (result.isAllowed()) {
                allowed = true;
            }
        }

        Action action = allowed? Action.ALLOW : Action.DENY;
        String message = allowed? "" : "No rule matches";

        return RelationResult.create(action, message);
    }
}
