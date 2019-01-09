package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class InstanceTest {

    @Test
    public void shouldTestVariables() {
        Inner i = new Inner();
        Assert.assertEquals(i.var, 10, "var");
    }

    private class Inner {
        private int var = 1;

        public Inner() {
            Assert.assertEquals(var, 1, "var");

            this.var = 10;
        }
    }
}
