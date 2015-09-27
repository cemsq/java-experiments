package properties.grabber;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reflection.exception.FieldFormatException;

/**
 *
 */
public class FieldGrabberHelperTest {

    @DataProvider
    public Object[][] provider() {
        return new Object[][]{
                {"testing 1 field", "name", "name", "", false},
                {"testing 2 fields", "object.name", "object", "name", false},
                {"testing 3 fields", "object.name.last", "object", "name.last", false},
                {"testing wrong field", "lastName.", "lastName", "it does not matter", true},
                {"testing empty field", "", "lastName", "it does not matter", true}
        };
    }

    @Test(dataProvider = "provider")
    public void shouldParseFieldNames(String testDesc, String fieldName, String first, String second, boolean shouldFail) {

        boolean failed = false;
        try {
            String[] str1 = FieldGrabberHelper.parseFieldName(fieldName);

            Assert.assertNotNull(str1, testDesc + ": null array");
            Assert.assertEquals(str1.length, 2, testDesc + ": array size != 2");
            Assert.assertEquals(str1[0], first, " first field wrong");
            Assert.assertEquals(str1[1], second, " second field wrong");

        } catch (FieldFormatException e) {
            failed = true;
            if (!shouldFail) {
                Assert.fail(testDesc + " should not fail", e);
            }
        }

        if (shouldFail && !failed) {
            Assert.fail(testDesc + " should fail, but it didn't");
        }
    }
}
