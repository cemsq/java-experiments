package com.performance;

import org.testng.annotations.Test;

/**
 *
 */
public class PrintingStringsTest extends PerformanceBaseTest {

    private static final Integer MAX_ITER = 5000;

    @Test
    public void withOutput() {
        execute(() -> System.out.println("a very large String a very large String a very large String a very large String a very large String a very large String"));
    }

    @Test
    public void noOut() {
        execute(() -> {});
    }

    private void execute(Runnable runnable) {
        for (int i = 0; i < MAX_ITER; i++) {
            runnable.run();
        }
    }
}
