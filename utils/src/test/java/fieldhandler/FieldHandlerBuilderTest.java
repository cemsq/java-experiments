package fieldhandler;

import com.cgm.storm.utils.fieldhandler.FieldHandler;
import com.cgm.storm.utils.fieldhandler.handlers.ArrayFieldHandler;
import com.cgm.storm.utils.fieldhandler.handlers.CompositeFieldHandler;
import com.cgm.storm.utils.fieldhandler.handlers.FieldHandlerFactory;
import com.cgm.storm.utils.fieldhandler.handlers.SimpleFieldHandler;
import com.cgm.storm.utils.reflection.exception.FieldFormatException;
import fieldhandler.example.Person;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class FieldHandlerBuilderTest {

    @Test(dataProvider = "tokensProvider")
    public void shouldParseFieldName(String fullName, String firstExpected, String secondExpected, boolean shouldFail) {
        try {
            String []tokens = FieldHandlerFactory.parseFieldName(fullName);

            Assert.assertNotNull(tokens, "tokens are null");
            Assert.assertEquals(tokens.length, 2, "wrong tokens size");
            Assert.assertEquals(tokens[0], firstExpected, "first token not match");
            Assert.assertEquals(tokens[1], secondExpected, "second token not match");

            Assert.assertFalse(shouldFail, "it should fail, but it didn't");

        } catch (FieldFormatException e) {
            if (!shouldFail) {
                Assert.fail(e.getMessage());
            }
        }
    }

    @Test(dataProvider = "createProperHandlerProvider")
    public void shouldCreateProperHandler(Class clazz, String field, Class<? extends FieldHandler> handler) {
        Assert.assertEquals(FieldHandlerFactory.create(clazz, field).getClass(), handler);
    }

    @DataProvider
    public Object[][] tokensProvider() {
        return new Object[][] {
                {"name", "name", "", false},
                {"person.name", "person", "name", false},
                {"person.pet.name", "person", "pet.name", false},
                {"", "", "", true},
                {"person.", "person", "it does not matter", true}
        };
    }

    @DataProvider
    public Object[][] createProperHandlerProvider() {
        return new Object[][]{
                {Person.class, "name", SimpleFieldHandler.class},
                {Person.class, "pet", SimpleFieldHandler.class},
                {Person.class, "books", SimpleFieldHandler.class},
                {Person.class, "pet.name", CompositeFieldHandler.class},
                {Person.class, "books.title", ArrayFieldHandler.class},
                {Person.class, "books.author.name", ArrayFieldHandler.class}
        };
    }
}
