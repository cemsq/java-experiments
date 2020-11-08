import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstNonRepeatingCharacterTest {

    @DataProvider(name = "provider")
    public static Object[][] provider() {
        return new Object[][] {
                {"", "_"},
                {"abcabc", "_"},
                {null, "_"},

                {"aaacbbbbbd", "c"},
        };
    }

    @Test(dataProvider = "provider")
    public void test(String value, String expected) {

        String result = FirstNonRepeatingCharacter.get(value);
        Assert.assertEquals(result, expected);
    }
}
