package com.reflection;

import org.testng.annotations.Test;

/**
 *
 */
public class GenericReflectionTest {

    @Test
    public void test1() {
        GenericClass<String> str = new GenericClass<>();

        str.add("dd");
    }
}
