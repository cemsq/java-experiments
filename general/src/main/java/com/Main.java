package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public int xxyyzz;

    public static void foo() {
        throw new NoSuchFieldError("ups!!!");
    }

    public static void bar() {
       throw new NullPointerException("revienta");
    }

    public static List<Integer> filter(List<Integer> list, IntegerFilter filter) {
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer n : list) {
            if (filter.filter(n)) {
                result.add(n);
            }
        }

        return result;
    }

    public static<T> List<T> filter2(List<T> list, GenericFilter<T> filter) {
        ArrayList<T> result = new ArrayList<>();

        for (T element : list) {
            if (filter.filter(element)) {
                result.add(element);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Person p = Person.create("Fox", new Dog("Scooby"));

        //foo();

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        final int k = 3;

        List l1 = filter(list, new IntegerFilter() {
            @Override
            public boolean filter(int n) {
                return n > k;
            }
        });

        List l2 = filter(list, new IntegerFilter() {
            @Override
            public boolean filter(int n) {
                return (n % 2) == 0;
            }
        });

        List<Dog> dogs = Arrays.asList(new Dog("Abc"), new Dog("Abbb"), new Dog("Bcc"), new Dog("Cgg"));
        List<Dog> l3 = filter2(dogs, new GenericFilter<Dog>() {
            @Override
            public boolean filter(Dog dog) {
                return dog.getName().startsWith("A");
            }
        });

        System.out.println(l1);
        System.out.println();
        System.out.println(l2);
        System.out.println();
        System.out.println(l3);
    }
}
