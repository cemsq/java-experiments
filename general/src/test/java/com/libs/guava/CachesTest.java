package com.libs.guava;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 *
 */
@Slf4j
public class CachesTest {

    @Test
    public void shouldRemoveAutomatically() throws InterruptedException {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .removalListener(n -> log.info("removing: [{}, {}]", n.getKey(), n.getValue()))
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer integer) {
                        return integer.toString();
                    }
                });

        cache.getUnchecked(1);
        cache.getUnchecked(2);
        cache.getUnchecked(3);

        Thread.sleep(5000);

        cache.getUnchecked(10);
    }
}
