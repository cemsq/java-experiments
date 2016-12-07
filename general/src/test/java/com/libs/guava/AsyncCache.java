package com.libs.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.mockito.cglib.proxy.CallbackFilter;
import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */

public class AsyncCache {

    final AtomicInteger value = new AtomicInteger(1);

    // nicely named threads are nice
    ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("MyCacheRefresher-pool-%d").setDaemon(true).build();
    ExecutorService parentExecutor = Executors.newSingleThreadExecutor(threadFactory);

    // create an executor that provide ListenableFuture instances
    final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(parentExecutor);


    final LoadingCache<String, ListenableFuture<Integer>> cache = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, ListenableFuture<Integer>>() {
                @Override
                public ListenableFuture<Integer> load(String key) throws Exception {

                    return executorService.submit(() -> {
                        System.out.println("Loading async");
                        Thread.sleep(10000);
                        return value.getAndIncrement();
                    });
                }
            });

    @Test
    public void shouldLoadAsync() throws InterruptedException, ExecutionException {
        ListenableFuture<Integer> foo = cache.getUnchecked("foo");

        System.out.println("is done: " + foo.isDone());
        while(!foo.isDone()) {
            Thread.sleep(1000);
            System.out.println("waiting...");
        }
        System.out.println("value: " + foo.get());
    }



}
