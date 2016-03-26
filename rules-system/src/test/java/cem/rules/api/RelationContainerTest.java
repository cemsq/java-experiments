package cem.rules.api;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RelationContainerTest {

    @Test(dataProvider = "dataProvider")
    public void test(RelationContainer<OrgUnitExtension> container, String pCh, String cCh, RelationResult relationResult) {
        OrgUnitExtension parent = new OrgUnitExtension();
        parent.add(pCh);

        OrgUnitExtension child = new OrgUnitExtension();
        child.add(cCh);

        RelationResult result = container.check(parent, child);

        Assert.assertEquals(result.getAction(), relationResult.getAction());
        Assert.assertEquals(result.getReason(), relationResult.getReason());
    }

    @DataProvider
    public Object[][] dataProvider() {
        RelationContainer<OrgUnitExtension> container = new RelationContainer<>();
        container.add(contains("S"), contains("P"), RelationResult.allow());
        container.add(contains("S"), contains("G"), RelationResult.allow());
        container.add(contains("P"), contains("C"), RelationResult.allow());
        container.add(contains("G"), contains("C"), RelationResult.allow());
        container.add(contains("C"), contains("A"), RelationResult.allow());

        container.add(contains("S"), contains("C"), RelationResult.deny("not allowed"));
        container.add(contains("S"), contains("A"), RelationResult.deny("not defined"));

        return new Object[][] {
                {container, "S", "P", RelationResult.allow()},
                {container, "S", "G", RelationResult.allow()},
                {container, "P", "C", RelationResult.allow()},
                {container, "G", "C", RelationResult.allow()},
                {container, "C", "A", RelationResult.allow()},

                {container, "S", "C", RelationResult.deny("not allowed")},
                {container, "S", "A", RelationResult.deny("not defined")},

                {container, "P", "S", RelationResult.deny("No rule matches")},
                {container, "P", "G", RelationResult.deny("No rule matches")},
                {container, "P", "P", RelationResult.deny("No rule matches")},
                {container, "P", "A", RelationResult.deny("No rule matches")},

                {container, "G", "S", RelationResult.deny("No rule matches")},
                {container, "G", "G", RelationResult.deny("No rule matches")},
                {container, "G", "P", RelationResult.deny("No rule matches")},
                {container, "G", "A", RelationResult.deny("No rule matches")},

                {container, "C", "S", RelationResult.deny("No rule matches")},
                {container, "C", "P", RelationResult.deny("No rule matches")},
                {container, "C", "G", RelationResult.deny("No rule matches")},
                {container, "C", "C", RelationResult.deny("No rule matches")},

                {container, "A", "S", RelationResult.deny("No rule matches")},
                {container, "A", "P", RelationResult.deny("No rule matches")},
                {container, "A", "G", RelationResult.deny("No rule matches")},
                {container, "A", "C", RelationResult.deny("No rule matches")},
        };
    }

    public RelationCondition<OrgUnitExtension> contains(String chr) {
        return oue -> oue.getCharacteristics().contains(chr);
    }

    public RelationCondition<OrgUnitExtension> notContains(String chr) {
        return oue -> !oue.getCharacteristics().contains(chr);
    }

}
