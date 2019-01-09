package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class TryWithResourcesTest {

    private boolean closed = false;

    @Test
    public void shouldCallCloseWhenFail() {

        try {
            try (Resource r = new Resource()) {

                Assert.assertEquals(closed, false, "closed");

                throw new RuntimeException("fail");
            }
        } catch (Exception e) {
            //
        }

        Assert.assertEquals(closed, true, "closed");
    }

    private class Resource implements AutoCloseable {

        @Override
        public void close() {
            closed = true;
        }
    }
}
