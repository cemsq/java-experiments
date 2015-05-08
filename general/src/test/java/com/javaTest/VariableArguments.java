package com.javaTest;

import org.testng.Assert;
import org.testng.annotations.Test;

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
        // unexpected result
        String str = getStringFromObjects(array);

        try {
            // unexpected result
            Assert.assertEquals(str, "1 2 3 4 5 6 7 8 9");
        } catch (AssertionError e) {
            System.out.println();
            e.printStackTrace();
        }
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

        String str = getStringFromObjects(namesA, namesB);
        try {
            Assert.assertEquals(str, "Napoleon Neron Dario Carlos");
        }catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    @Test
    public void usingArraysAsArguments() {
        Name []namesA = {new Name("Josef"), new Name("Norbert")};
        Name []namesB = {new Name("Dennis"), new Name("Florian")};

        String str = getStringFromDeepObjects(namesA, namesB, new Name("Cesar"));
        System.out.println("printing: " + str);

        Assert.assertEquals(str, "Josef Norbert Dennis Florian Cesar");
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

    public String getStringFromDeepObjects(Object... args) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Object arg : args) {
            if (!first) {
                sb.append(" ");
            }
            first = false;

            if (isArray(arg)) {
                sb.append(getStringFromDeepObjects((Object[]) arg));
            } else {
                sb.append(arg);
            }
        }

        return sb.toString();
    }


    public boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }
}
