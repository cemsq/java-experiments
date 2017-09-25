package reflection;

import com.google.common.collect.Lists;
import lombok.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldAccessorImplTest {


    private Person person = new Person(1, "john", Arrays.asList("t1", "t2", "t3"));

    @Test
    public void shouldGetInteger() {
        FieldAccessor<Person> accessor = Reflection.fieldAccessor(Person.class, "id", FieldAccessMode.Field);

        String id = accessor.get(person, String.class);
        Assert.assertEquals(id, "1", "id");
    }

    @Test
    public void shouldGetString() {
        FieldAccessor<Person> accessor = Reflection.fieldAccessor(Person.class, "name", FieldAccessMode.Field);

        String name = accessor.get(person);
        Assert.assertEquals(name, "john", "name");
    }

    @Test
    public void shouldGetList() {
        FieldAccessor<Person> accessor = Reflection.fieldAccessor(Person.class, "things", FieldAccessMode.Field);

        //noinspection unchecked
        List<String> list = accessor.get(person);
        Assert.assertEqualsNoOrder(list.toArray(new String[0]), new String[]{"t1", "t2", "t3"});
    }

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", 123);
        map.put("code", "abc");
        map.put("names", Arrays.asList("a", "b", "c"));

        Integer intId = (Integer)map.get("id");
        String id = (String)map.get("id");
    }

    @Data
    public class Person {
        private Integer id;
        private String name;
        private List<String> things;

        public Person(Integer id, String name, List<String> things) {
            this.id = id;
            this.name = name;
            this.things = things;
        }
    }
}
