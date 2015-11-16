import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 */
public class Milk2Test {

    @Test(dataProvider = "compactProvider")
    public void compactTest(int a1, int a2, int b1, int b2, Block expected) {
        Block b = new Block(a1, a2).compact(new Block(b1, b2));

        if (expected != null) {
            Assert.assertNotNull(b, "compact return null");
            Assert.assertEquals(b, expected);
        } else {
            Assert.assertNull(b, "compact return a value");
        }

    }

    @Test
    public void usingASet() {
        BlockSet list = new BlockSet();

        list.add(new Block(1, 2));
        list.add(new Block(7, 9));
        list.add(new Block(2, 1));
        list.add(new Block(3, 5));
        list.add(new Block(3, 4));
        list.add(new Block(3, 6));
        list.add(new Block(5, 8));
        list.add(new Block(10, 18));
        list.add(new Block(30, 32));

        System.out.println(list);
        // max job time
        int mjt = 0;
        int mft = 0;

        Block last = null;
        for (Block that : list.list()) {
            mjt = Math.max(mjt, that.size());

            if (last != null) {
                mft = Math.max(mft, that.getIni() - last.getEnd());
            }

            last = that;
        }
        System.out.println(mjt + "  " + mft);
    }

    @DataProvider
    public Object[][] compactProvider() {
        return new Object[][] {
                {1, 1, 1, 2, new Block(1, 2)},
                {3, 2, 3, 1, new Block(1, 2)},
                {300, 700, 250, 400, new Block(250, 700)},
                {300, 700, 300, 900, new Block(300, 900)},
                {100, 700, 250, 800, new Block(100, 800)},
                {100, 1000, 500, 700, new Block(100, 1000)},
                {500, 700, 100, 1000, new Block(100, 1000)},
                {100, 200, 400, 500, null}
        };
    }
}



