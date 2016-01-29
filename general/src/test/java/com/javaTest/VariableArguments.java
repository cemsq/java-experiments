package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class VariableArguments {

    @Test
    public void usingIntArguments() {
        String str1 = getStringFromInt(1);
        String str2 = getStringFromInt(2, 3);
        String str3 = getStringFromInt();

        int []array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String str4 = getStringFromInt(array);

        Assert.assertEquals(str1, "1");
        Assert.assertEquals(str2, "2 3");
        Assert.assertEquals(str3, "");
        Assert.assertEquals(str4, "1 2 3 4 5 6 7 8 9");
    }

    @Test
    public void usingIntAsObjectArguments() {
        String str1 = getStringFromObjects(1);
        String str2 = getStringFromObjects(2, 3);
        String str3 = getStringFromObjects();

        Assert.assertEquals(str1, "1");
        Assert.assertEquals(str2, "2 3");
        Assert.assertEquals(str3, "");
    }

    @Test
    public void confusingPrimitiveArrayArgumentToObject() {
        int []array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // java assume "array" as a unique object
        String str = getStringFromObjects(array);

        // should fail
        Assert.assertEquals(str, "1 2 3 4 5 6 7 8 9");
    }

    @Test
    public void usingNameClass() {
        Name []names = {new Name("Julio"), new Name("Napoleon"), new Name("Neron")};

        String str = getStringFromObjects(names);

        Assert.assertEquals(str, "Julio Napoleon Neron");
    }

    @Test
    public void confusingArgumentsExpected_ArraysReceived() {
        Name []namesA = {new Name("Napoleon"), new Name("Neron")};
        Name []namesB = {new Name("Dario"), new Name("Carlos")};

        // it will take/assume 2 arguments: namesA, namesB
        // it is an unexpected result
        String str = getStringFromObjects(namesA, namesB);

        Assert.assertEquals(str, "Napoleon Neron Dario Carlos");

    }

    @Test
    public void takingElementsFromAnArrays() {
        Name []namesA = {new Name("Josef"), new Name("Norbert")};
        Name []namesB = {new Name("Dennis"), new Name("Florian")};

        String str = getStringFromDeepObjects(namesA, namesB, new Name("Cesar"));
        System.out.println("printing: " + str);

        Assert.assertEquals(str, "Josef Norbert Dennis Florian Cesar");
    }

    @Test
    public void takingElementsFromAnArrayList() {
        List<Name> list = Arrays.asList(new Name("Josef")
                , new Name("Norbert")
                , new Name("Dennis"));

        Name []namesB = {new Name("Florian"), new Name("Cesar")};

        String str = getStringFromDeepObjects(list, namesB);
        System.out.println("printing: " + str);

        Assert.assertEquals(str, "Josef Norbert Dennis Florian Cesar");
    }

    @Test
    public void gettingListFromArgs() {
        List<Name> list = (List<Name>)asList(new Name("Cesar"), Arrays.asList(new Name("Dario"), new Name("Mora")), new Name("Mariya"));

        String str = getStringFromDeepObjects(list);
        Assert.assertEquals(str, "Cesar Dario Mora Mariya");
    }

    public String getStringFromInt(int ...args) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int arg: args) {
            if (!first) {
                sb.append(" ");
            }
            first = false;

            sb.append(arg);
        }

        return sb.toString();
    }

    public String getStringFromObjects(Object... args) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for (Object arg: args) {
            if (!first) {
                sb.append(" ");
            }
            first = false;

            sb.append(arg);
        }

        return sb.toString();
    }

    public static String getStringFromDeepObjects(Object... args) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Object arg : args) {
            if (!first) {
                sb.append(" ");
            }
            first = false;

            if (isArray(arg)) {
                sb.append(getStringFromDeepObjects((Object[]) arg));
            } else if (isList(arg)) {

                sb.append(getStringFromDeepObjects(((List) arg).toArray()));
            } else {
                sb.append(arg);
            }
        }

        return sb.toString();
    }

    @Test
    public List asList(Object ...args) {
        List list = new ArrayList<>();

        for (Object arg : args) {
            List returned;
            if (isArray(arg)) {
                returned = asList((Object[])arg);

            } else if (isList(arg)) {
                returned = asList(((List)arg).toArray());

            } else {
                returned = Collections.singletonList(arg);
            }

            list.addAll(returned);
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
