package com.javaTest;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Queues;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class QueueTest {

    @Test
    public void stackPoll() {
        Deque<Integer> stack = Lists.newLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        List<Integer> output = Lists.newArrayList();
        while (!stack.isEmpty()) {
            Integer val = stack.poll();
            output.add(val);
        }

        Assert.assertEquals(output.toString(), "[5, 4, 3, 2, 1]");
    }
}
