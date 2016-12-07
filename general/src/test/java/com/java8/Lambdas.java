package com.java8;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class Lambdas {

    @Test
    public void testingWithIterable() {
        List<Integer> list = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        Integer integer = Iterables.find(list, x -> x > 0);

        Iterable<Integer> integers = Iterables.filter(list, x -> x > 0);
        List<Integer> filtered = Lists.newArrayList(integers);

        list.stream().filter(x -> x > 0).toArray(length -> new Integer[length]);


        Assert.assertEquals(filtered.size(), 5);
    }

    @Test
    public void collect() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 2, 5);
        Set<Integer> list2 = list.stream()
                .collect(Collectors.toSet());

        Assert.assertEquals(list2.size(), 5);
    }
}
