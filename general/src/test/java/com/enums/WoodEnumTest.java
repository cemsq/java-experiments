package com.enums;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class WoodEnumTest {

    @Test
    public void testingInstances() {
        Wood maple = Wood.MAPLE;
        Wood m = Wood.MAPLE;

        Assert.assertEquals(maple, m, "Don't match the Enums");
    }
}
