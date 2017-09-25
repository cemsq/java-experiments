package com.javaTest;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class ListIteratorTest {

    @Test
    public void removing() {
        List<Integer> list = Lists.newArrayList(1);
        Iterator<Integer> it = list.iterator();
        it.next();
        it.remove();

        Assert.assertEquals(list.size(), 0);
    }
}
