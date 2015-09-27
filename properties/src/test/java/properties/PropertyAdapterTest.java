package properties;

import fieldhandler.example.*;
import fieldhandler.example.Number;
import org.testng.Assert;
import org.testng.annotations.Test;
import properties.properties.PropertySet;

import java.util.Arrays;

/**
 *
 */
public class PropertyAdapterTest {

    @Test
    public void shouldCreateProperties() {
        PropertyAdapter<Person> adapter = createAdapter();
        Person person = createPerson();

        PropertySet properties = adapter.createPropertySet(person);

        Assert.assertNotNull(properties.getCategories());
        Assert.assertEquals(properties.getCategories().size(), 2);
        Assert.assertEquals(properties.getCategories().get(0).getItems().size(), 3);
        Assert.assertEquals(properties.getCategories().get(1).getItems().size(), 1);

        Assert.assertEquals(properties.getCategories().get(0).getItems().get(0).getValue(), "Cesar");
        Assert.assertEquals(properties.getCategories().get(0).getItems().get(1).getValue(), "29");
        Assert.assertEquals(properties.getCategories().get(0).getItems().get(2).getValue(), "123");
    }

    @Test
    public void shouldStorePropertiesToObject() {
        PropertyAdapter<Person> adapter = createAdapter();
        Person person = createPerson();
        PropertySet properties = adapter.createPropertySet(person);

        Person p = new Person();
        adapter.storePropertiesToObject(properties, p);

        Assert.assertEquals(p.getName(), "Cesar");
        Assert.assertEquals(p.getAge(), 29);
        Assert.assertEquals(p.getHouse().getNumber().getCode(), "123");
    }

    @Test
    public void shouldUpdatePropertiesFromObject() {
        PropertyAdapter<Person> adapter = createAdapter();
        Person person = createPerson();
        PropertySet properties = adapter.createPropertySet(person);

        person.setName("Dario");
        person.setAge(15);
        person.setHouse(new House(new Number("abc")));
        person.setPet(new Pet("Nala"));

        adapter.updateProperties(properties, person);

        Assert.assertEquals(properties.getCategory("General").getItem("name").getValue(), "Dario");
        Assert.assertEquals(properties.getCategory("General").getItem("age").getValue(), "15");
        Assert.assertEquals(properties.getCategory("General").getItem("house.number.code").getValue(), "abc");
        Assert.assertEquals(properties.getCategory("Info").getItem("pet.name").getValue(), "Nala");
    }

    @Test
    public void shouldCreateObject() {
        PropertyAdapter<Person> adapter = createAdapter();
        Person person = createPerson();

        PropertySet properties = adapter.createPropertySet(person);

        Person p = adapter.createObject(properties);

        Assert.assertEquals(p.getName(), "Cesar");
        Assert.assertEquals(p.getAge(), 29);
        Assert.assertEquals(p.getHouse().getNumber().getCode(), "123");
    }

    public PropertyAdapter<Person> createAdapter() {
        PropertyAdapter<Person> adapter = new PropertyAdapter<>(Person.class, "PersonPropertyAdapter");

        adapter.defineProperty("General", "name", "FirstName", true, true);
        adapter.defineProperty("General", "age", "Age", true, true);
        adapter.defineProperty("General", "house.number.code", "House number", true, true);
        adapter.defineProperty("Info", "pet.name", "Per Name", true, true);

        return adapter;
    }

    public Person createPerson() {
        Person person = PersonProvider.create("Cesar", 29, Arrays.asList("1", "2", "3"), new Pet("Linda"), new House(new Number("abc")));
        return person;
    }
}
