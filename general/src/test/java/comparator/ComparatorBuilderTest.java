package comparator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;

public class ComparatorBuilderTest {

    private Comparator<Person> c = ComparatorBuilder.start(Person.class)
            .then(Person::getId)
            .then(Person::getName)
            .build();

    @Test
    public void shouldCompareFlatFields() {

        Person p1 = new Person().setId(1).setName("cesar");
        Person p2 = new Person().setId(1).setName("cesar").setDesc("abc");

        Assert.assertEquals(c.compare(p1, p2), 0);
        Assert.assertEquals(c.compare(p1, p2.setId(10)), -1);
    }

    @Test
    public void shouldHandleNullFields() {
        Comparator<Person> c = ComparatorBuilder.start(Person.class)
                .onNull(ComparatorBuilder.NullStrategy.FIRST)
                .then(Person::getId)
                .then(Person::getName)
                .build();

        Person p1 = new Person().setId(null).setName("cesar");
        Person p2 = new Person().setId(1).setName("cesar").setDesc("abc");

        Assert.assertEquals(c.compare(p1, p2), -1);
        Assert.assertEquals(c.compare(p2, p1), -1);
    }

    @Test
    public void shouldCompareNullValues() {
        Assert.assertEquals(c.compare(null, null), 0, "[null - null]");
        Assert.assertEquals(c.compare(null, new Person()), -1, "[null - person]");
        Assert.assertEquals(c.compare(new Person(), null), 1, "[person - null]");
    }
}
