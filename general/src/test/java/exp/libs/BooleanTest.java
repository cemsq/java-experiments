package exp.libs;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class BooleanTest {

    @DataProvider
    public Object[][] provider() {
        return new Object[][] {
                {"true", true},
                {"True", true},
                {"tRUe", true},

                {"false", false},
                {"False", false},
                {"asdf", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "provider")
    public void convertingStringToBoolean(String value, boolean expected) {
        boolean result = Boolean.valueOf(value);

        Assert.assertEquals(result, expected,
                "error testing [value: '" + value + "']. [expected: '"+expected+"']");
    }
}
