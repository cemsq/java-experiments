package com.generics.fluent;

import org.testng.annotations.Test;

/**
 *
 */
public class Wrong {

    private static class ParentClass {

        public ParentClass parentMethod() {
            return this;
        }

        public ParentClass upper() {
            return this;
        }
    }

    private static class ChildClass extends ParentClass {

        @Override
        public ChildClass parentMethod() {
            super.parentMethod();
            return this;
        }

        @Override
        public ChildClass upper() {
            super.upper();
            return this;
        }

        public ChildClass first() {
            return this;
        }
    }

    @Test
    public void test() {
        ChildClass child = new ChildClass();

        child.first()
                .first()
                .first()
                .upper()
                .parentMethod()
                .first()
                .parentMethod()
                .parentMethod()
                .parentMethod()
                .first()
                .first();

        ParentClass a = child.parentMethod();
    }
}
