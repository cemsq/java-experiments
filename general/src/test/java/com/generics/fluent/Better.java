package com.generics.fluent;

import org.testng.annotations.Test;

/**
 *
 */
public class Better {

    private static class Parent<T extends Parent<T>> {

        @SuppressWarnings("unchecked")
        protected T cast() {
            return (T) this;
        }

        public T parentMethod() {
            return cast();
        }

        public T upper() {
            return cast();
        }

        public T setBounds() {
            return cast();
        }
    }

    private static class Child extends Parent<Child> {

        public Child first() {
            return this;
        }
    }

    @Test
    public void test() {
        Child child = new Child();

        child.first()
                .first()
                .setBounds()
                .first()
                .upper()
                .parentMethod()
                .first()
                .parentMethod()
                .parentMethod()
                .parentMethod()
                .first()
                .first();

    }
}
