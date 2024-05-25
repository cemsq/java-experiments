package com.javaTest;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
@Slf4j
public class ThreadTest {

    @Test
    public void shouldInterrupt() throws InterruptedException {
        Thread loop = new Thread(() -> {
            while (true) {

                try {
                    log.info("working...");
//                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                if (Thread.interrupted()) {
//                    log.info("done..");
//                    break;
//                }
            }
        });

        loop.start();
        Thread.sleep(100);
        loop.interrupt();
    }

    @Test
    public void t1() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<?> f1 = pool.submit(() -> {
            log.info("{}: failing", Thread.currentThread().getName());
            throw new IllegalArgumentException("it fails in first submit");
        });

        Future<?> f2 = pool.submit(() -> {
            log.info("{}: successful", Thread.currentThread().getName());
        });

        Thread.sleep(5000);
    }
}
