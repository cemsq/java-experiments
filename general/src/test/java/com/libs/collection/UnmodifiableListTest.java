package com.libs.collection;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class UnmodifiableListTest {

    @Test
    public void test() {
        List<Integer> original = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> unmodifiable = Collections.unmodifiableList(original);

        Assert.assertEquals(unmodifiable.size(), 5);
        original.add(6);
        original.add(7);
        original.add(8);

        Assert.assertEquals(unmodifiable.size(), 5);
    }
}

