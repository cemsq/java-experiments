package com.libs.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.testng.annotations.Test;

/**
 *
 */
public class JodaTest {

    @Test
    public void test() {
        String str = "YYJ";
        DateTimeFormatter format = DateTimeFormat.forPattern(str);

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        DateTime parse = DateTime.parse("14001", format);

        System.out.println(parse);
    }

    @Test
    public void test2() {
        DateTime dateTime = DateTime.parse("20150229", DateTimeFormat.forPattern("yyyyMMdd"));

        System.out.println(dateTime.year().isLeap());

        System.out.println(dateTime);
    }
}
