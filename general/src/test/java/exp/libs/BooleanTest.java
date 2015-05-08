package exp.libs;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class BooleanTest {
    @Test
    public void convertingStringToBoolean_shouldBe_true() {
        boolean true1 = Boolean.valueOf("true");
        boolean true2 = Boolean.valueOf("True");
        boolean true3 = Boolean.valueOf("tRUe");

        Assert.assertEquals(true1, true);
        Assert.assertEquals(true2, true);
        Assert.assertEquals(true3, true);
    }

    @Test
    public void convertingStringToBoolean_shouldBe_false() {
        boolean false1 = Boolean.valueOf("False");
        boolean false2 = Boolean.valueOf("");
        boolean false3 = Boolean.valueOf(null);

        Assert.assertEquals(false1, false);
        Assert.assertEquals(false2, false);
        Assert.assertEquals(false3, false);
    }
}
