package fieldhandler;

import com.cgm.storm.utils.fieldhandler.FieldHandler;
import com.cgm.storm.utils.fieldhandler.handlers.FieldHandlerFactory;
import fieldhandler.example.Book;
import fieldhandler.example.Person;
import fieldhandler.example.Pet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FieldHandlerTest {

    @Test(dataProvider = "getterProvider")
    public void shouldGetValue(Class clazz, String field, Object object, Object value) {
        FieldHandler handler = FieldHandlerFactory.create(clazz, field);

        Assert.assertEquals(handler.get(object), value);
    }

    @Test(dataProvider = "setterProvider")
    public void shouldSetValue(Class clazz, String field, Object object, Object value) {
        FieldHandler handler = FieldHandlerFactory.create(clazz, field);

        handler.set(object, value);
        Assert.assertEquals(handler.get(object), value);
    }

    @Test
    public void shouldMaintainValuesInCompositeHandler() {
        Person person = new Person();

        FieldHandler petNameHandler = FieldHandlerFactory.create(Person.class, "pet.name");
        FieldHandler petAgeHandler = FieldHandlerFactory.create(Person.class, "pet.age");

        petNameHandler.set(person, "Nala");
        petAgeHandler.set(person, 5);

        Assert.assertEquals(person.getPet().getName(), "Nala");
        Assert.assertEquals(person.getPet().getAge(), 5);
    }

    @Test
    public void shouldMaintainValuesInArrayHandler() {
        Person person = new Person();

        List<String> titles = Arrays.asList("Book A", "Book B");
        FieldHandler handler = FieldHandlerFactory.create(Person.class, "books.title");
        handler.set(person, titles);

        Assert.assertNotNull(person.getBooks());
        Assert.assertTrue(person.getBooks().size() > 0);
        Assert.assertEquals(person.getBooks().get(0).getTitle(), "Book A");


        handler = FieldHandlerFactory.create(Person.class, "books.author.name");
        List<String> authorsName = Arrays.asList("A", "B", "C");
        handler.set(person, authorsName);

        handler = FieldHandlerFactory.create(Person.class, "books.author.age");
        List<Integer> authorsAge = Arrays.asList(30, 50, 40);
        handler.set(person, authorsAge);

        Assert.assertNotNull(person.getBooks());
        Assert.assertTrue(person.getBooks().size() > 0);
        Assert.assertEquals(person.getBooks().get(0).getAuthor().getName(), "A");

        Assert.assertEquals(person.getBooks().get(0).getTitle(), "Book A");
    }

    @DataProvider
    public Object[][] getterProvider() {
        List<Book> books = new ArrayList<>();

        Book bookA = new Book();
        Person authorA = new Person();
        authorA.setName("Author A");
        bookA.setTitle("Book A");
        bookA.setAuthor(authorA);
        books.add(bookA);

        Book bookB = new Book();
        Person authorB = new Person();
        authorB.setName("Author B");
        bookB.setTitle("Book B");
        bookB.setAuthor(authorB);
        books.add(bookB);

        Person person = new Person();
        Pet pet = new Pet();
        pet.setName("Goofy");
        person.setName("John Doe");
        person.setAge(40);
        person.setPet(pet);
        person.setBooks(books);

        return tests(person);
    }

    @DataProvider
    public Object[][] setterProvider() {
        Person person = new Person();

        return tests(person);
    }

    public Object[][] tests(Person person) {

        return new Object[][] {
                {Person.class, "name", person, "John Doe"},
                {Person.class, "age", person, 40},

                {Person.class, "pet.name", person, "Goofy"},
                {Person.class, "books.title", person, Arrays.asList("Book A", "Book B")},
                {Person.class, "books.author.name", person, Arrays.asList("Author A", "Author B")},
        };
    }
}
