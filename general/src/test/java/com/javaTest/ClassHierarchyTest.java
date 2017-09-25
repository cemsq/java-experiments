package com.javaTest;

import org.testng.annotations.Test;

/**
 *
 */
public class ClassHierarchyTest {

    @Test
    public void test() {
        A a = new A("a1");

//        A.B b = new A.B("b1");

        A.B b1 = new A("a2").new B("b1");
        A.B b2 = a.new B("b2");



        a.a();
        b1.b();
        b2.b();
    }

    public class A {
        private String value;

        public A(String value) {
            this.value = value;
        }

        void a() {
            System.out.println(value);
        }




        public class B {
            private String value;

            public B(String value) {
                this.value = value;
            }

            void b() {
                a();
                System.out.println(value);
            }
        }
    }
}
