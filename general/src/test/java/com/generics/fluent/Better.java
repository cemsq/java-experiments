package com.generics.fluent;

/**
 *
 */
public class Better {

    public class SuperClass<T extends SuperClass<? super T>> {

        public T a() {
            return (T)this;
        }
    }

    public class ChildClass extends SuperClass<ChildClass> {

        public ChildClass b() {
            return this;
        }
    }

    public void test() {
        ChildClass c = new ChildClass();
        c.a().a().b().a().b().b().b();
    }
}
