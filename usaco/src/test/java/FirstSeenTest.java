import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class FirstSeenTest {

    @DataProvider(name = "provider")
    public static Object[][] provider() {
        return new Object[][] {
                {new int[]{}, -1},
                {null, -1},

                {new int[]{1, 2, 3, 4, 5 , 6, 7, 8, 9, 10, 11, 12, 13, 14}, -1},
                {new int[]{2, 1, 3, 5, 3, 2}, 3},
                {new int[]{2, 2}, 2},
                {new int[]{1,2,1,3,5}, 1},
        };
    }

    @Test(dataProvider = "provider")
    public void test(int[] values, int expected) {
        int result = FirstDuplicate.fistDuplicate(values);
        Assert.assertEquals(result, expected, "Array: " + Arrays.toString(values));
    }
}
