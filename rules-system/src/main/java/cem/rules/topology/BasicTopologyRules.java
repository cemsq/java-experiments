package cem.rules.topology;

import cem.rules.api.RelationChecker;
import cem.rules.api.RelationContainer;
import cem.rules.api.RelationResult;
import cem.rules.test.OrgUnit;
import cem.rules.test.Type;

import static cem.rules.api.RelationResult.*;
import static cem.rules.topology.OrgUnitConditions.*;

public class BasicTopologyRules extends RelationContainer<OrgUnit> implements RelationChecker<OrgUnit> {

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

    @Override
    public RelationResult check(OrgUnit left, OrgUnit right) {
        return super.check(left, right);
    }
}
