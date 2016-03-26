package cem.rules.topology;

import cem.rules.test.Type;

import static cem.rules.api.RelationResult.allow;
import static cem.rules.api.RelationResult.deny;

public class ProviderRulesTest extends BasicTopologyRulesTest {

    public void setup() {
        ProviderRules providerRules = new ProviderRules();

        defineTest(providerRules, system(), orgUnit(Type.Provider), allow());
        defineTest(providerRules, system(), orgUnit(Type.Group), allow());
        defineTest(providerRules, orgUnit(Type.Provider), orgUnit(Type.Clinic), allow());
        defineTest(providerRules, orgUnit(Type.Group), orgUnit(Type.Clinic), allow());
        defineTest(providerRules, orgUnit(Type.Clinic), orgUnit(Type.Address), allow());

        defineTest(providerRules, orgUnit(Type.Address), orgUnit(Type.Clinic), deny("Address not allowed as parent"));
    }
}


