package properties.grabber;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import properties.example.*;
import properties.example.Number;

/**
 *
 */
public class FieldGrabberTest {

    @Test(dataProvider = "getterProvider")
    public void shouldGetValue(Class clazz, String field, Object object, Object value) {
        FieldGrabber grabber = new FieldGrabber(clazz, field);

        Assert.assertEquals(grabber.getValue(object), value);
    }

    @Test(dataProvider = "setterProvider")
    public void shouldSetValue(Class clazz, String field, Object object, Object value) {
        FieldGrabber grabber = new FieldGrabber(clazz, field);
        grabber.setValue(object, value);
        Assert.assertEquals(grabber.getValue(object), value);
    }

    @DataProvider
    public Object[][] getterProvider() {
        Person person = createPerson();

        return new Object[][] {
                {Person.class, "name", person, "Cesar"},
                {Person.class, "house.number.code", person, "123"},
                {Person.class, "pet.name", person, "Linda"}
        };
    }

    @DataProvider
    public Object[][] setterProvider() {
        Person person = createPerson();

        return new Object[][] {
                {Person.class, "name", person, "Dario"},
                {Person.class, "age", person, 20},
                {Person.class, "house.number.code", person, "abcd"},
                {Person.class, "pet.name", person, "Nala"}
        };
    }

    public Person createPerson() {
        return new Person("Cesar", new House(new Number("123")), new Pet("Linda"));
    }
}

