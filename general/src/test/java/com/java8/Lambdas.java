package com.java8;

import com.google.common.collect.Iterables;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
public class Lambdas {

    @Test
    public void testingWithIterable() {
        List<Integer> list = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        list.stream()
                .filter(x -> x > 5)
                .forEach(str -> System.out.println(str));

    }
}
