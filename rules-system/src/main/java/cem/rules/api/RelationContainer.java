package cem.rules.api;

import com.google.common.collect.Lists;
import static cem.rules.api.RelationResult.*;

import java.util.List;

public class RelationContainer<OBJECT> implements RelationChecker<OBJECT> {

    private List<Relation<OBJECT>> relations = Lists.newArrayList();

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

        return allowed? allow() : deny("No rule matches");
    }
}
