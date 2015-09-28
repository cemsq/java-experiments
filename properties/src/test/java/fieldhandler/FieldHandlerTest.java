package fieldhandler;

import fieldhandler.example.*;
import fieldhandler.example.Number;
import fieldhandler.example.item.Ingredient;
import fieldhandler.example.item.Item;
import fieldhandler.example.item.ItemIngredient;
import fieldhandler.handlers.ArrayFieldHandler;
import fieldhandler.handlers.CompositeFieldHandler;
import fieldhandler.handlers.FieldHandlerFactory;
import fieldhandler.handlers.SimpleFieldHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reflection.exception.FieldNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FieldHandlerTest {

    // ***************************************************************
    // Tests
    // ***************************************************************

    @Test(dataProvider = "creatingProvider")
    public void creatingHandler(Class clazz, String field, boolean shouldFail) {
        try {
            FieldHandler handler = FieldHandlerFactory.create(clazz, field);
            Assert.assertNotNull(handler);

            Assert.assertFalse(shouldFail, "it should fail, but it didn't");
        }catch (FieldNotFoundException e) {
            Assert.assertTrue(shouldFail, "it shouldn't fail. " + e.getMessage());
        }
    }

    @Test(dataProvider = "creatorProvider")
    public void shouldCreateProperHandler(Class clazz, String field, Class<? extends FieldHandler> handler) {
        Assert.assertEquals(FieldHandlerFactory.create(clazz, field).getClass(), handler);
    }

    @Test(dataProvider = "getterProvider")
    public void shouldGetValue(FieldHandler grabber, Object object, Object value) {
        Assert.assertEquals(grabber.get(object), value);
    }

    @Test(dataProvider = "setterProvider")
    public void shouldSetValue(FieldHandler grabber, Object object, Object value) {
        grabber.set(object, value);
        Assert.assertEquals(grabber.get(object), value);
    }

    // ***************************************************************
    // Providers
    // ***************************************************************

    @DataProvider
    public Object[][] creatingProvider() {
        return new Object[][]{
                {Person.class, "name", false},
                {Person.class, "namen", true},
                {Person.class, "pet.name", false},
                {Person.class, "pets.name", false},
                {Item.class, "itemIngredients.ingredient.name", false},
                {Person.class, "item.itemIngredients.ingredient.name", false},
        };
    }

    @DataProvider
    public Object[][] creatorProvider() {
        return new Object[][]{
                {Person.class, "name", SimpleFieldHandler.class},
                {Person.class, "pet.name", CompositeFieldHandler.class},
                {Person.class, "pets.name", ArrayFieldHandler.class},
                {Item.class, "itemIngredients.ingredient.name", ArrayFieldHandler.class},
                {Person.class, "item.itemIngredients.ingredient.name", CompositeFieldHandler.class},
        };
    }

    @DataProvider
    public Object[][] getterProvider() {
        Person person = new Person();
        Item item = new Item();

        person.setName("Cesar");
        person.setAge(29);
        person.setNumbers(Arrays.asList("1", "2", "3"));
        person.setPet(new Pet("Linda"));
        person.setHouse(new House(new Number("abc")));
        person.setPets(Arrays.asList(new Pet("Linda"), new Pet("Canela"), new Pet("Nala")));
        person.setItem(item);

        List<ItemIngredient> list = new ArrayList<>();

        ItemIngredient ii;
        Ingredient ingr;

        ii = new ItemIngredient();
        ingr = new Ingredient();
        ingr.setName("A");
        ii.setIngredient(ingr);
        list.add(ii);

        ii = new ItemIngredient();
        ingr = new Ingredient();
        ingr.setName("B");
        ii.setIngredient(ingr);
        list.add(ii);

        ii = new ItemIngredient();
        ingr = new Ingredient();
        ingr.setName("C");
        ii.setIngredient(ingr);
        list.add(ii);
        item.setItemIngredients(list);

        return tests(person);
    }

    @DataProvider
    public Object[][] setterProvider() {
        Person person = PersonProvider.create(null, 0, null, null, null);


        return tests(person);
    }

    public Object[][] tests(Person person) {


        return new Object[][] {
                // flat field handler
                {FieldHandlerFactory.create(Person.class, "name"), person, "Cesar"},
                {FieldHandlerFactory.create(Person.class, "age"), person, 29},
                {FieldHandlerFactory.create(Person.class, "numbers"), person, Arrays.asList("1", "2", "3")},

                // composite field handler
                {FieldHandlerFactory.create(Person.class, "pet.name"), person, "Linda"},
                {FieldHandlerFactory.create(Person.class, "house.number.code"), person, "abc"},

                // array field handler
                {FieldHandlerFactory.create(Person.class, "pets.name"), person, Arrays.asList("Linda", "Canela", "Nala")},

                // generic handler
                {FieldHandlerFactory.create(Person.class, "name"), person, "Cesar"},
                {FieldHandlerFactory.create(Person.class, "house.number.code"), person, "abc"},
                {FieldHandlerFactory.create(Person.class, "item.itemIngredients.ingredient.name"), person, Arrays.asList("A", "B", "C")},
        };
    }
}
