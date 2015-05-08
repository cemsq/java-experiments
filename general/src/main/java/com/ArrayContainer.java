package com;

import java.util.Arrays;

/**
 *
 */
public class ArrayContainer {
    String array[] = {"A", "B", "C"};

    public ArrayContainer() {
    }

    public String[] getArray() {
        return array;
    }

    public String[] getContent() {
        String []content = new String[array.length];

        //System.arraycopy(array, 0, content, 0, array.length);
        for (int i=0; i<array.length; i++) {
            content[i] = array[i];
        }

        return content;
    }

    @Override
    public String toString() {
        return "ArrayContainer{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
