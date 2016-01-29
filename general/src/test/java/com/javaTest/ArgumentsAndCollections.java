package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 *
 */
public class ArgumentsAndCollections {

    @Test
    public void testingObjects() {
        List<Name> list = method(new Name("Cesar"), new Name("Dario"));

        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void testingList() {
        List<Name> list = method(Arrays.asList(new Name("Cesar"), new Name("Dario")), new Name("Mora"));

        Assert.assertEquals(list.size(), 3);
    }

    public static<T> List<T> method(Object ...args) {
        return asList(args);
    }

    public static<T> List<T> asList(Object ...args) {
        List<T> list = new ArrayList<>();

        for (Object arg : args) {
//            if (arg instanceof ) {
//
//            }
            List<T> returned;
            if (isArray(arg)) {
                returned = asList((T[])arg);

            } else if (isList(arg)) {
                returned = asList((T[])((List<T>)arg).toArray());

            } else {
                returned = Collections.singletonList((T)arg);
            }

            Collections.addAll(list, (T[])returned.toArray());
        }

        return list;
    }

    public static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    public static boolean isList(Object obj) {
        return obj instanceof List;
    }
}
