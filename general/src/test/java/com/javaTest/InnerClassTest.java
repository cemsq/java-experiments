package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class InnerClassTest {

    @Test
    public void test() {
        Internal internal = new Internal(30){{
            this.value = 20;
        }};

        print("after creation", internal);
    }

    public class Internal {
        protected Integer value;
        public Internal(int value) {
            this.value = value;
            print("direct from constructor",this);
        }
    }

    public static void print(String info, Internal internal) {
        System.out.println(info + " value = " + internal.value);
    }
}
