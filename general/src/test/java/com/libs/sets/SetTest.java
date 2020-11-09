package com.libs.sets;

import com.google.common.collect.Sets;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.Comparator;
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

    @Test
    public void uniqueByInstance() {

        Person p1 = new Person("ABC");
        Person p2 = new Person("abc");

        Set<Person> set = Sets.newLinkedHashSet();
        set.add(p1);
        set.add(p2);

        Assert.assertEquals(set.toString(), "[ABC, abc]");

        p1.setName("ABC-new");
        Assert.assertEquals(set.toString(), "[ABC-new, abc]");

        set.add(p1);
        Assert.assertEquals(set.toString(), "[ABC-new, abc]");
    }

    @Test
    public void shouldModifyElements() {
        Set<Person> persons = Sets.newTreeSet(Comparator.comparing(Person::getId));

        persons.add(new Person(4, "d"));
        persons.add(new Person(3, "c"));
        persons.add(new Person(2, "b"));
        persons.add(new Person(2, "b"));
        persons.add(new Person(2, "b"));

        Person person1 = new Person(1, "a");
        persons.add(person1);
        persons.add(person1);
        persons.add(person1);

        Assert.assertEquals(persons.size(), 4, "size");

        Assert.assertTrue(persons.contains(new Person(2, "asc")), "contains");
        Assert.assertTrue(persons.containsAll(
                Lists.newArrayList(new Person(2, "asdf"), new Person(4, "abc"))
        ), "containsall");

        Person[] array = persons.toArray(new Person[]{});
        Assert.assertEquals(array[0].getName(), "a", "name not match after insertion");

        person1.setName("carl");
        array = persons.toArray(new Person[]{});
        Assert.assertEquals(array[0].getName(), "carl", "name not match after insertion");
    }


    class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Person(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (!(o instanceof Person)) {
//                return false;
//            }
//
//            Person person = (Person) o;
//
//            return !(name != null ? !name.equals(person.name) : person.name != null);
//
//        }
//
//        @Override
//        public int hashCode() {
//            return name != null ? name.hashCode() : 0;
//        }
    }
}


