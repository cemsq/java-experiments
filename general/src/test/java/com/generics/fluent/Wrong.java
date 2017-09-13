package com.generics.fluent;

import org.testng.annotations.Test;

/**
 *
 */
public class Wrong {

    public class SuperClass {

        public SuperClass a() {
            return this;
        }
    }

    public class ChildClass extends SuperClass {

        @Override
        public ChildClass a() {
            return this;
        }

        public ChildClass b() {
            return this;
        }
    }

    @Test
    public void test() {
        ChildClass c = new ChildClass();

        c.b().a().b().a().a().a().b().b();

        SuperClass a = c.a();
    }
}
