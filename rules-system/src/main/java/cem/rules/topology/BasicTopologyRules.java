package cem.rules.topology;

import cem.rules.api.*;
import cem.rules.test.OrgUnit;
import cem.rules.test.Type;

import static cem.rules.topology.TopologyRulesFactory.*;

public class BasicTopologyRules implements RelationChecker<OrgUnit> {

    private RelationContainer<OrgUnit> container = new RelationContainer<>();

    public BasicTopologyRules() {
        add(isNull(), any(), deny("Parent is null"));
        add(any(), isNull(), deny("Child is null"));
        add(hasNoType(), any(), deny("Parent without type"));
        add(any(), hasNoType(), deny("Child without type"));
        add(hasNoCharacteristics(), any(), deny("Parent without characteristics"));
        add(any(), hasNoCharacteristics(), deny("Child without characteristics"));

        add(any(), isSystem(), deny("Not allowed to modify System node"));
        add(any(), is(Type.System), deny("System type not allowed"));
    }

    public void add(RelationCondition<OrgUnit> parent, RelationCondition<OrgUnit> child, ResultProvider provider) {
        container.add(new Relation<>(parent, child, provider));
    }

    @Override
    public RelationResult check(OrgUnit left, OrgUnit right) {
        return container.check(left, right);
    }
}
