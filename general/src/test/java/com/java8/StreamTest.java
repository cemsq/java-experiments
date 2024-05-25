package com.java8;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class StreamTest {

    @Test
    public void shouldStop() {
        List<String> source = Lists.newArrayList("cat", "dog", "umbrella", "fox", "two");
        List<String> dest = Lists.newArrayList();

        forEach(source.stream(), str -> {
            boolean odd = str.length() % 2 != 0;
            if (odd) {
                dest.add(str);
            }
            return odd;
        });

        Assert.assertEquals(dest, Lists.newArrayList("cat", "dog"));
    }

    @Test
    public void shouldStop2() {

        AtomicInteger count = new AtomicInteger(0);
        Stream<Integer> stream = Stream.generate(() -> {
            if (count.getAndIncrement() < 10) {
                return (int) (Math.random() * 100);
            } else {
                return null;
            }
        });

        List<Integer> list = streamBreakable(stream, Objects::isNull)
                .collect(Collectors.toList());

        System.out.println(list);
    }

    public static <T> void forEach(Stream<T> stream, Predicate<T> shouldContinue) {
        Spliterator<T> spliterator = stream.spliterator();
        boolean hasNext = true;
        AtomicBoolean advance = new AtomicBoolean(true);

        while (hasNext && advance.get()) {
            hasNext = spliterator.tryAdvance(t -> {
                boolean value = shouldContinue.test(t);
                advance.set(value);
            });
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static <T> Stream<T> streamBreakable(Stream<T> stream, Predicate<T> stopCondition) {
        Stream.Builder<T> builder = Stream.builder();
        stream.map(t -> {
                    boolean stop = stopCondition.test(t);
                    if (!stop) {
                        builder.add(t);
                    }
                    return stop;
                })
                .filter(result -> result)
                .findFirst();

        return builder.build();
    }
}
