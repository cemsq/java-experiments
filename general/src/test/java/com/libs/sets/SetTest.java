package com.libs.sets;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;


/**
 *
 */
public class SetTest {

    @Test
    public void uniqueElements() {
        Set<Person> set = new HashSet<>();
        set.add(new Person("ABC"));
        set.add(new Person("abc"));

        Person p = new Person("xxx");
        set.add(p);

        p.setName("yyy");
        set.add(p);

        Assert.assertEquals(set.size(), 2);
        System.out.println(set);
    }
}


class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        return !(name != null ? !name.equals(person.name) : person.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}