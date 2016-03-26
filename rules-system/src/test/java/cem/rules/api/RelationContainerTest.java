package cem.rules.api;

import cem.rules.test.OrgUnitExtension;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RelationContainerTest {

    @Test(dataProvider = "dataProvider")
    public void test(RelationContainer<OrgUnitExtension> container, String pCh, String cCh, Action action) {
        OrgUnitExtension parent = new OrgUnitExtension();
        parent.add(pCh);

        OrgUnitExtension child = new OrgUnitExtension();
        child.add(cCh);

        RelationResult result = container.check(parent, child);

        Assert.assertEquals(result.getAction(), action);
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

        return new Object[][] {
                {container, "S", "P", Action.ALLOW},
                {container, "S", "G", Action.ALLOW},
                {container, "P", "C", Action.ALLOW},
                {container, "G", "C", Action.ALLOW},
                {container, "C", "A", Action.ALLOW},

                {container, "S", "C", Action.DENY},
                {container, "S", "A", Action.DENY},

                {container, "P", "S", Action.DENY},
                {container, "P", "G", Action.DENY},
                {container, "P", "P", Action.DENY},
                {container, "P", "A", Action.DENY},

                {container, "G", "S", Action.DENY},
                {container, "G", "G", Action.DENY},
                {container, "G", "P", Action.DENY},
                {container, "G", "A", Action.DENY},

                {container, "C", "S", Action.DENY},
                {container, "C", "P", Action.DENY},
                {container, "C", "G", Action.DENY},
                {container, "C", "C", Action.DENY},

                {container, "A", "S", Action.DENY},
                {container, "A", "P", Action.DENY},
                {container, "A", "G", Action.DENY},
                {container, "A", "C", Action.DENY},
        };
    }

    public RelationCondition<OrgUnitExtension> contains(String chr) {
        return oue -> oue.getCharacteristics().contains(chr);
    }

    public RelationCondition<OrgUnitExtension> notContains(String chr) {
        return oue -> !oue.getCharacteristics().contains(chr);
    }

}
