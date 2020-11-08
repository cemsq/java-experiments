package com;

import com.test_ng.ToString;
import lombok.Getter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 */
public class ToStringTest {

    private ToString s;

    @BeforeMethod
    public void setUp() {
        s = new ToString();
    }

    @Test
    public void shouldMapNull() {
        Assert.assertEquals(s.apply(null), "null");
    }

    @Test
    public void shouldMapNotNull() {
        Assert.assertEquals(s.apply(123), "123");
    }

    @Test
    public void shouldMapWithCustomMapping() {
        s.map(Integer.class, i -> "--- 123 ---");
        Assert.assertEquals(s.apply(123), "--- 123 ---");
    }

    @Test
    public void shouldMapArray() {
        Integer []array = new Integer[] {1, 2, 3};
        Assert.assertEquals(s.apply(array), "1, 2, 3");
    }

    @Test
    public void shouldMapArrayWithCustomMapping() {
        Integer []array = new Integer[] {1, 2, 3};
        s.map(Integer.class, i -> "'" + i + "'");
        Assert.assertEquals(s.apply(array), "'1', '2', '3'");
    }

    @Test
    public void shouldMapArrayWithDefinedDelimiter() {
        Integer []array = new Integer[] {1, 2, 3};
        Assert.assertEquals(s.arrayDelimiter("::").apply(array), "1::2::3");
    }

    @Test
    public void shouldMapBeanToString() {
        Person p = new Person(456, "Me");
        Assert.assertEquals(s.apply(p), "toString: 456 Me");
    }

    @Test
    public void shouldMapBeanSimpleGetter() {
        Person p = new Person(456, "Me");
        s.map(Person.class, Person::getName);
        Assert.assertEquals(s.apply(p), "Me", "person without definition");
    }

    @Test
    public void shouldMapBeanSimpleGetterWithCustomMapping() {
        Person p = new Person(456, "Me");
        s.map(Person.class, Person::getName)
                .map(String.class, s -> "#" + s + "#");
        Assert.assertEquals(s.apply(p), "#Me#", "person without definition");
    }

    @Test
    public void shouldMapBeanArrayOfGetter() {
        Person p = new Person(456, "Me");
        s.map(Person.class, Person::getId, Person::getName);
        Assert.assertEquals(s.apply(p), "456, Me", "person without definition");
    }

    @Test
    public void shouldMapBeanArrayOfGetterWithDelimiter() {
        Person p = new Person(456, "Me");
        s.map(Person.class, Person::getId, Person::getName)
                .arrayDelimiter("-");
        Assert.assertEquals(s.apply(p), "456-Me", "person without definition");
    }

    @Test
    public void shouldMapBeanArrayOfGetterWithDelimiterAndCustomMapping() {
        Person p = new Person(456, "Me");
        s.map(Person.class, Person::getId, Person::getName)
                .map(Integer.class, i -> "id" + i + "")
                .map(String.class, s -> "'" + s + "'")
                .arrayDelimiter("::");
        Assert.assertEquals(s.apply(p), "id456::'Me'", "person without definition");
    }

    @Getter
    private static class Person {
        private final  Integer id;
        private final String name;

        private Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "toString: " + id + " " + name;
        }
    }
}
