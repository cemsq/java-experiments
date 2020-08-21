package com.javaTest;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
public class ListsTest {

    @Test
    public void gettingFirstElementForEmptyList() {
        List<Name> list = Lists.newArrayList();

        Name name = list.get(0);

        Assert.assertNull(name);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldIterateOnNullLists() {
        List<String> list = null;

        for (String s : list) {
            System.out.println(s);
        }

    }

    @Test
    public void subList() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        int size = list.size() * 10;

        List<Integer> subList = list.subList(0, 0);
        subList.add(7);

        Assert.assertEquals(list.toString(), "[1, 2, 3, 4, 5]");
    }
}
