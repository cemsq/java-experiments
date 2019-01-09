package com.libs.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class JodaTest {

    @Test
    public void shouldParseDateTime() {
        String str = "YYJ";
        DateTimeFormatter format = DateTimeFormat.forPattern(str);

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        DateTime parse = DateTime.parse("14001", format);

        System.out.println(parse);
    }

    @Test
    public void shouldCompareMilliseconds() throws InterruptedException {
        LocalDateTime init = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.now();

        System.out.println(init);
        System.out.println(now);


        System.out.println(now.getMillisOfDay() - init.getMillisOfDay());
        ;
    }

    @Test
    public void shouldPrintYearFor_1_1_2018() {
        DateTime date = DateTime.parse("2018/1/1", DateTimeFormat.forPattern("YYYY/MM/dd"));

        Assert.assertEquals(date.getYear(), 2018);
    }

    @Test
    public void print() {
        DateTimeFormatter format = DateTimeFormat.forPattern("YYYY-MM-dd'T'HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.toString(format));

        System.out.println(LocalDate.now());
    }

    @Test
    public void t1() {
        Integer s1 = 1;
        Integer s2 = 1;

        Integer s3 = new Integer(1);

        System.out.println("s1 == s2 " + (s1 == s2));
        System.out.println("s1.equals(s2) " + s1.equals(s2));

        System.out.println("s1 == s3 " + (s1 == s3));
        System.out.println("s1.equals(s3) " + s1.equals(s3));
    }

    @Test
    public void d() {
        for (int i = 1; i <= 12; i++) {
            System.out.println("12 - " + i + " = " + (12 - i));
        }
    }

    @Test
    public void test2() {
        DateTime dateTime = DateTime.parse("20150229", DateTimeFormat.forPattern("yyyyMMdd"));

        System.out.println(dateTime.year().isLeap());

        System.out.println(dateTime);
    }

    @Test
    public void shouldBeBefore() {
        LocalDate now = LocalDate.now();
        LocalDate after = now.plusDays(12).minusDays(12);

        Assert.assertTrue(now.compareTo(after) == 0);
    }

    @DataProvider(name = "dateProvider")
    public Object[][] dateProvider() {
        int start = 2000;
        int end = 3000;
        Object[][] tests = new Object[end - start][];
        for (int i = 0; i < end - start; i++) {
            int year = start + i;
            tests[i] = new Object[] {year};
        }

        return tests;
    }

    @Test(dataProvider = "dateProvider")
    public void shouldTestWeekyear(int iYear) {
            LocalDate date = date("01/01/" + iYear);

            int year = date.getYear();
            int weekyear = date.getWeekyear();
            Assert.assertEquals(weekyear, year, "year - weekYear");
    }

    @Test(dataProvider = "dateProvider")
    public void shouldTestYear(int iYear) {
        LocalDate date = date("01/01/" + iYear);

        int year = date.getYear();
        Assert.assertEquals(year, iYear, "expected year");
    }

    @Test(dataProvider = "dateProvider")
    public void shouldTestWeekOfWeekYear(int iYear) {
        LocalDate date = date("01/01/" + iYear);

        int weekOfWeekyear = date.getWeekOfWeekyear();
        Assert.assertEquals(weekOfWeekyear, 1);
    }

    @Test(dataProvider = "dateProvider")
    public void shouldTestLastWeek(int iYear) {
        LocalDate date = date("06/06/" + iYear);
        int lastWeek = date.weekOfWeekyear().getMaximumValue();

        Assert.assertEquals(lastWeek, 52, "weeks count");
    }

    @Test
    public void showWeekOfWeekYear() {
        LocalDate date = date("05/06/2017");

        System.out.println(date);

        System.out.println(date.weekOfWeekyear().setCopy(52));
        System.out.println(date.weekOfWeekyear().setCopy(20));
        System.out.println(date.weekOfWeekyear().setCopy(5));
    }

    private LocalDate date(String strDate) {
        return LocalDate.parse(strDate, DateTimeFormat.forPattern("dd/MM/yyyy"));
    }
}
