package org.cem.jkal;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 *
 */
public class SortTest {

    @Test
    public void t1() {
        int []a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 20;
        int index = bSearch(a, target);
        System.out.println(index);
    }

    @Test
    public void t2() {
        int []a = generate(10);

        print(a);
        sort(a);

        assertOrder(a);
        print(a);
    }

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int candidate = array[i];
            int index = bSearch(array, 0, i, candidate);

            moveToRight(array, index, i);
            array[index] = candidate;
        }
    }

    private void moveToRight(int[] array, int from, int to) {
        for (int i = to; i > from; i--) {
            array[i] = array[i - 1];
        }
    }

    public int bSearch(int[] array, int v) {
        return bSearch(array, 0, array.length - 1, v);
    }

    public int bSearch(int[] array, int l, int r, int target) {
        if (l > r) {
            return l;
        }

        int m = (l + r) / 2;
        int middle = array[m];
        if (middle == target) {
            return m;
        } else return target < middle
                ? bSearch(array, l, m - 1, target)
                : bSearch(array, m + 1, r, target);
    }

    private void assertOrder(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                Assert.fail(Arrays.toString(a));
            }
        }
    }

    public void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    private int[] generate(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        return a;
    }
}
