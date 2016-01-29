import org.testng.Assert;

/**
 *
 */
public class FridayTest {

    @org.testng.annotations.Test
    public void test1() {


        for (int day = 0; day < 30; day++) {
            System.out.println("day= " + day + "   ->  " + (day+2) % 7);

        }
    }

    @org.testng.annotations.Test
    public void testLeapYear() {
        for (int year = 1800; year <= 2100; year++) {
            String str = String.format("year = %s,  leap = %s", year, friday.isLeapYear(year));
            System.out.println(str);
        }
    }

    @org.testng.annotations.Test
    public void testingMonths() {
        for (int month = 1; month <= 12; month++) {
            String str = String.format("month = %s,  days= %s", month, friday.daysForMonth(month, 1905));

            System.out.println(str);
        }
    }

    @org.testng.annotations.Test
    public void testSubString() {
        Assert.assertEquals(0, "asdfadf".substring(0,0).length());
    }
}
