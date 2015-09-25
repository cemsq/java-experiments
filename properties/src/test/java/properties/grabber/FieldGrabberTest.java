package properties.grabber;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import properties.example.*;
import properties.example.Number;
import properties.example.type.EnumType;
import properties.example.type.RootType;

import java.util.Arrays;

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

    @Test(dataProvider = "typeProvider")
    public void shouldGetType(Class clazz, String field, Class type) {
        FieldGrabber grabber = new FieldGrabber(clazz, field);

        Assert.assertEquals(grabber.getType(), type);
    }

    // -----------------------------------------------------------
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

    @DataProvider
    public Object[][] typeProvider() {
        return new Object[][] {
                {RootType.class, "string", String.class},
                {RootType.class, "integer", Integer.class},
                {RootType.class, "anInt", int.class},
                {RootType.class, "aBoolean", Boolean.class},
                {RootType.class, "child.aDouble", Double.class},
                {RootType.class, "child.string", String.class},
                {RootType.class, "child.sub.aDouble", double.class},
                {RootType.class, "child.sub.enumeration", EnumType.class}
        };
    }

    public Person createPerson() {
        return new Person("Cesar", new House(new Number("123")), new Pet("Linda"));
    }
}

