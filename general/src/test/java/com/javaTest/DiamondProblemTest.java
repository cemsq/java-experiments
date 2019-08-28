package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class DiamondProblemTest {

    @DataProvider
    public Object[][] provider() {
        return new Object[][]{
                {new AB(), "AB"},
                {new ADefB(), "ADef"},
                {new BDefA(), "BDef"},
                {new ADefBDef_ADef(), "ADef"},

        };
    }

    @Test(dataProvider = "provider")
    public void test(Parent p, String expected) {
        Assert.assertEquals(p.get(), expected, p.getClass().getSimpleName());
    }

    private class AB implements A, B {

        @Override
        public String get() {
            return "AB";
        }
    }

    private class ADefB implements ADef, B { }

    private class BDefA implements A, BDef { }

    // it does not compile
    // private class ADefBDef implements ADef, BDef {

    // }

    private class ADefBDef_ADef implements ADef, BDef {

        @Override
        public String get() {
            return ADef.super.get();
        }
    }

    private interface Parent {

        String get();
    }

    private interface A extends Parent {}

    private interface B extends Parent {}

    private interface ADef extends Parent {

        @Override
        default String get() {
            return "ADef";
        }
    }

    private interface BDef extends Parent {

        @Override
        default String get() {
            return "BDef";
        }
    }
}
