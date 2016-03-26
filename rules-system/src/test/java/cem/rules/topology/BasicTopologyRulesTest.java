package cem.rules.topology;

import cem.rules.api.RelationChecker;
import cem.rules.api.RelationResult;
import cem.rules.test.OrgUnit;
import cem.rules.test.Type;
import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static cem.rules.api.RelationResult.*;

public class BasicTopologyRulesTest {

    private List<Object[]> tests = Lists.newArrayList();

    public void setup() {
        BasicTopologyRules basicRules = new BasicTopologyRules();
        defineTest(basicRules, null, orgUnit(1, Type.Address), deny("Parent is null"));
        defineTest(basicRules, orgUnit(1, Type.Address), null, deny("Child is null"));
        defineTest(basicRules, orgUnit(1, null), orgUnit(1, Type.Address), deny("Parent without type"));
        defineTest(basicRules, orgUnit(1, Type.Address), orgUnit(1, null), deny("Child without type"));
        defineTest(basicRules, orgUnit(1, Type.Empty), orgUnit(1, Type.Address), deny("Parent without characteristics"));
        defineTest(basicRules, orgUnit(1, Type.Address), orgUnit(1, Type.Empty), deny("Child without characteristics"));

        defineTest(basicRules, orgUnit(1, Type.Address), orgUnit(2, Type.System), deny("System type not allowed"));
        defineTest(basicRules, orgUnit(1, Type.Address), orgUnit(1, Type.Address), deny("Not allowed to modify System node"));
    }

    @Test(dataProvider = "dataProvider")
    public void test(RelationChecker<OrgUnit> checker, OrgUnit parent, OrgUnit child, RelationResult relationResult) {

        RelationResult result = checker.check(parent, child);

        String message = "";
//        if (parent != null && child != null) {
//            message = String.format("parent: %s      child: %s", parent.getType(), child.getType());
//        }

        Assert.assertEquals(result.getAction(), relationResult.getAction());
        Assert.assertEquals(result.getReason(), relationResult.getReason());
    }

    public final void defineTest(
            RelationChecker<OrgUnit> checker,
            OrgUnit parent,
            OrgUnit child,
            RelationResult relationResult) {

        tests.add(new Object[]{checker, parent, child, relationResult});
    }

    @DataProvider
    public Object[][] dataProvider() {
        setup();

        int i = 0;
        Object[][] objects = new Object[tests.size()][];
        for (Object[] obj : tests) {
            objects[i++] = obj;
        }

        return objects;
    }

    public OrgUnit system() {
        return new OrgUnit(OrgUnitConditions.SYSTEM_ID, Type.System);
    }

    public OrgUnit orgUnit(Type type) {
        return new OrgUnit((int)(Math.random()*100), type);
    }

    public OrgUnit orgUnit(Integer id, Type type) {
        return new OrgUnit(id, type);
    }
}

