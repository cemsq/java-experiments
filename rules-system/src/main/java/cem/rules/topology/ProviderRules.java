package cem.rules.topology;

import cem.rules.api.RelationChecker;
import cem.rules.test.OrgUnit;
import cem.rules.test.Type;

import static cem.rules.topology.TopologyRulesFactory.*;

public class ProviderRules extends BasicTopologyRules implements RelationChecker<OrgUnit> {

    public ProviderRules() {
        super();

        add(isSystem(), is(Type.Provider), allow());
        add(isSystem(), is(Type.Group), allow());
        add(is(Type.Provider), is(Type.Clinic), allow());
        add(is(Type.Group), is(Type.Clinic), allow());
        add(is(Type.Clinic), is(Type.Address), allow());

        add(is(Type.Address), any(), deny("Address not allowed as parent"));
    }
}
