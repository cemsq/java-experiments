import org.testng.Assert;
import org.testng.annotations.Test;
import util.StringsUtil;

/**
 *
 */
public class StringsUtilTest {

    @Test
    public void shouldCapitalize() {
        Assert.assertEquals(StringsUtil.capitalize("test"), "Test");
    }
}
