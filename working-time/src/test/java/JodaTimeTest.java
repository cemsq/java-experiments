import cem.wtime.WorkingTime;
import org.joda.time.LocalTime;
import org.testng.Assert;
import org.testng.annotations.Test;


public class JodaTimeTest {

    @Test
    public void test() {


        LocalTime time = LocalTime.parse("10:00");
        Assert.assertEquals(time.getHourOfDay(), 10);
        Assert.assertEquals(time.getMinuteOfHour(), 0);

        time = LocalTime.parse("13:00");
        Assert.assertEquals(time.getHourOfDay(), 13);
        Assert.assertEquals(time.getMinuteOfHour(), 0);

        time = LocalTime.parse("18:35");
        Assert.assertEquals(time.getHourOfDay(), 18);
        Assert.assertEquals(time.getMinuteOfHour(), 35);

    }

    @Test
    public void shouldCreateWorkingTime() {
        WorkingTime time = new WorkingTime(LocalTime.parse("10:20"));
        Assert.assertEquals(time.getTimeInMinutes(), 620);

        time = new WorkingTime(LocalTime.parse("1:20"));
        Assert.assertEquals(time.getTimeInMinutes(), 80);

        time = new WorkingTime(LocalTime.parse("10:20"));
        Assert.assertEquals(time.getTimeInMinutes(), 620);
    }

    @Test
    public void shouldPlusWorkingTime() {
        WorkingTime timeA = new WorkingTime(LocalTime.parse("1:20"));
        WorkingTime timeB = new WorkingTime(LocalTime.parse("1:00"));

        WorkingTime plus = timeA.plus(timeB);
        Assert.assertEquals(plus.getTimeInMinutes(), 140);
    }

    @Test
    public void shouldMinusWorkingTime() {
        WorkingTime timeA = new WorkingTime(LocalTime.parse("1:20"));
        WorkingTime timeB = new WorkingTime(LocalTime.parse("1:00"));

        WorkingTime plus = timeA.minus(timeB);
        Assert.assertEquals(plus.getTimeInMinutes(), 20);
    }

    @Test
    public void shouldReturn0WhenBIsBigger() {
        WorkingTime timeA = new WorkingTime(LocalTime.parse("1:00"));
        WorkingTime timeB = new WorkingTime(LocalTime.parse("1:20"));

        WorkingTime minus = timeA.minus(timeB);
        Assert.assertEquals(minus.getTimeInMinutes(), 0);
    }
}
