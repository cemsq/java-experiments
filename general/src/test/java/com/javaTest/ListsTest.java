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
}
