package com.javaTest;

import com.google.common.collect.Lists;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Stack;

/**
 *
 */
public class StackTest {

    @Test
    public void toList() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        List<Integer> list = Lists.newArrayList(stack);
        Assert.assertEquals(list, Lists.newArrayList(4, 3, 2, 1));
    }
}
