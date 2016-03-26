package cem.rules;

public class ProviderRulesTest {

//    ProviderRules providerRules = new ProviderRules();
//
//    @DataProvider
//    public Object[][] dataProvider() {
//        return new Object[][] {
//                {"S", "P", Action.ALLOW},
//                {"S", "G", Action.ALLOW},
//                {"P", "C", Action.ALLOW},
//                {"G", "C", Action.ALLOW},
//                {"C", "A", Action.ALLOW},
//
//                {"S", "C", Action.DENY},
//                {"S", "A", Action.DENY},
//
//                {"P", "S", Action.DENY},
//                {"P", "G", Action.DENY},
//                {"P", "P", Action.DENY},
//                {"P", "A", Action.DENY},
//
//                {"G", "S", Action.DENY},
//                {"G", "G", Action.DENY},
//                {"G", "P", Action.DENY},
//                {"G", "A", Action.DENY},
//
//                {"C", "S", Action.DENY},
//                {"C", "P", Action.DENY},
//                {"C", "G", Action.DENY},
//                {"C", "C", Action.DENY},
//
//                {"A", "S", Action.DENY},
//                {"A", "P", Action.DENY},
//                {"A", "G", Action.DENY},
//                {"A", "C", Action.DENY},
//        };
//    }
//
//    @Test(dataProvider = "dataProvider")
//    public void shouldMatchAction(String parent, String child, Action action) {
//        OrgUnitExtension parentHolder = new OrgUnitExtension();
//        parentHolder.add(parent);
//
//        OrgUnitExtension childHolder = new OrgUnitExtension();
//        childHolder.add(child);
//
//        RelationResult result = providerRules.check(parentHolder, childHolder);
//
//        Assert.assertEquals(result.getAction(), action);
//    }
}
