package com.yzm.base.java8.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class LocalDateTimeDemo {

    public static void main(String[] args) {
//        localDate01();
//        localDate02();
//        localDate03();
//        localDate04();

//        localTime01();
//        localTime02();
//        localTime03();

//        demo01();
//        demo02();
        demo03();
//        demo04();
    }

    private static void demo01() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);

        LocalDateTime of = LocalDateTime.of(2022, 9, 10, 12, 30, 45);
        System.out.println("of = " + of);

        LocalDateTime parse = LocalDateTime.parse("2022-08-11 09:23:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("parse = " + parse);

        LocalDateTime localDateTime = LocalDate.now().atTime(LocalTime.now());
        LocalDateTime localDateTime2 = LocalTime.now().atDate(LocalDate.now());
        LocalDateTime localDateTime3 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("localDateTime = " + localDateTime);
        System.out.println("localDateTime2 = " + localDateTime2);
        System.out.println("localDateTime3 = " + localDateTime3);

        LocalDateTime ofInstant = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("ofInstant = " + ofInstant);
    }

    private static void demo02() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        System.out.println("年：" + now.getYear());
        System.out.println("月：" + now.getMonthValue());
        System.out.println("日：" + now.getDayOfMonth());
        System.out.println("时：" + now.getHour());
        System.out.println("分：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());
        System.out.println();
        //get == getLong
        System.out.println("年：" + now.get(ChronoField.YEAR));
        System.out.println("月：" + now.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("日：" + now.get(ChronoField.DAY_OF_MONTH));
        System.out.println("时：" + now.get(ChronoField.HOUR_OF_DAY));
        System.out.println("分：" + now.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("秒：" + now.get(ChronoField.SECOND_OF_MINUTE));
    }

    private static void demo03() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(2022, 9, 10, 12, 30, 45);
        System.out.println("格式化：" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("比较：" + now.isAfter(of));
        System.out.println("比较：" + now.isBefore(of));

        //plusYears、plusHours、minusYears、minusSeconds
        System.out.println("减2年：" + now.minus(2, ChronoUnit.YEARS));
        System.out.println("加3小时：" + now.plus(3, ChronoUnit.HOURS));

        //withYear、withMonth、withHour、withSecond...
        System.out.println("修改当前为20号：" + now.with(ChronoField.DAY_OF_MONTH, 20));
        System.out.println("修改当前为20秒：" + now.with(ChronoField.SECOND_OF_MINUTE, 20));

        System.out.println("相差几个月：" + now.until(of, ChronoUnit.MONTHS));
    }

    private static void demo04() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();

        // Date 转 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime = " + localDateTime);
        LocalDateTime localDateTime2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime2 = " + localDateTime2);

        // LocalDateTime 转 Date
        Date from = Date.from(now.toInstant(ZoneOffset.of("+8")));
        System.out.println("from = " + from);
        Date from2 = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("from2 = " + from2);
    }

    //------------------------------------------- LocalTime -------------------------------------------
    private static void localTime01() {
        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);

        LocalTime from = LocalTime.from(LocalDateTime.now());
        System.out.println("from = " + from);

        LocalTime of = LocalTime.of(13, 50, 30);
        System.out.println("of = " + of);

        LocalTime parse = LocalTime.parse("12:30:03", DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("parse = " + parse);
    }

    private static void localTime02() {
        LocalTime now = LocalTime.now();
        System.out.println("时(24小时制)：" + now.getHour());
        System.out.println("分：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());
        System.out.println();
        System.out.println("时(24小时制)：" + now.get(ChronoField.HOUR_OF_DAY));
        System.out.println("时(12小时制)：" + now.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("分：" + now.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("一天(1440)中第几分：：" + now.get(ChronoField.MINUTE_OF_DAY));
        System.out.println("秒：" + now.get(ChronoField.SECOND_OF_MINUTE));
        System.out.println("一天(86400)中第几秒：" + now.get(ChronoField.SECOND_OF_DAY));
    }

    private static void localTime03() {
        LocalTime now = LocalTime.now();
        System.out.println("格式化：" + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("转换：" + now.atDate(LocalDate.now()));

        LocalTime of = LocalTime.of(20, 12, 30);

        System.out.println("比较：" + now.isAfter(of));
        System.out.println("比较：" + now.isBefore(of));

        System.out.println("修改时钟：" + now.withHour(20));
        System.out.println("修改分钟：" + now.withMinute(20));
        System.out.println("修改时钟：" + now.with(ChronoField.HOUR_OF_DAY, 20));
        System.out.println("修改秒钟：" + now.with(ChronoField.SECOND_OF_MINUTE, 20));

        System.out.println("减30分钟 = " + now.minus(30, ChronoUnit.MINUTES));
        System.out.println("加2小时 = " + now.plus(2, ChronoUnit.HOURS));

        System.out.println("相差多少秒:" + now.until(of, ChronoUnit.SECONDS));
    }

    //------------------------------------------- LocalDate -------------------------------------------

    private static void localDate01() {
        // 获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        // 获取指定日期对象
        LocalDate of = LocalDate.of(2022, 9, 10);
        System.out.println("of = " + of);

        LocalDate from = LocalDate.from(LocalDateTime.now());
        System.out.println("from = " + from);

        // 将日期字符串，根据格式化转换成日期对象
        LocalDate parse = LocalDate.parse("2020&10&04", DateTimeFormatter.ofPattern("yyyy&MM&dd"));
        System.out.println("parse = " + parse);

        //Date转换LocalDate
        LocalDate localDate2 = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDate2 = " + localDate2);
    }

    private static void localDate02() {
        LocalDate now = LocalDate.now();
        System.out.println("年 = " + now.getYear());
        System.out.println("月份对应的英文名称大写 = " + now.getMonth());
        System.out.println("月份 = " + now.getMonthValue()); // 1-12
        System.out.println("今天几号 = " + now.getDayOfMonth()); // 1-31
        System.out.println("今天星期几对应的英文名称大写 = " + now.getDayOfWeek());
        System.out.println("今天星期几 = " + now.getDayOfWeek().getValue());
        System.out.println("今天是当前年里的第几天 = " + now.getDayOfYear()); // 1-366
        System.out.println();
        System.out.println("年 = " + now.get(ChronoField.YEAR));
        System.out.println("月份 = " + now.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("今天几号 = " + now.get(ChronoField.DAY_OF_MONTH));
        System.out.println("今天星期几 = " + now.get(ChronoField.DAY_OF_WEEK));
        System.out.println("今天是当前年里的第几天 = " + now.get(ChronoField.DAY_OF_YEAR));
        System.out.println();
        System.out.println("年 = " + now.getLong(ChronoField.YEAR));
        System.out.println("月份 = " + now.getLong(ChronoField.MONTH_OF_YEAR));
        System.out.println("今天几号 = " + now.getLong(ChronoField.DAY_OF_MONTH));
        System.out.println("今天星期几 = " + now.getLong(ChronoField.DAY_OF_WEEK));
        System.out.println("今天是当前年里的第几天 = " + now.getLong(ChronoField.DAY_OF_YEAR));

    }

    private static void localDate03() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2023, 9, 11);
        System.out.println("日期格式化：" + now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        System.out.println("判断日期早晚：" + now.isAfter(of));
        System.out.println("判断日期早晚：" + now.isBefore(of));

        System.out.println("减3天 = " + now.minus(3, ChronoUnit.DAYS));
        System.out.println("减2月 = " + now.minus(2, ChronoUnit.MONTHS));
        System.out.println("加2周 = " + now.plus(2, ChronoUnit.WEEKS));
        System.out.println("加2天 = " + now.plusDays(2));

        System.out.println("相差几天：" + now.until(of, ChronoUnit.DAYS));
        System.out.println("相差几月：" + now.until(of, ChronoUnit.MONTHS));
    }

    private static void localDate04() {
        LocalDate now = LocalDate.now();

        System.out.println("修改年份：" + now.withYear(2023));
        System.out.println("修改月份：" + now.withMonth(2));
        System.out.println("修改天月：" + now.withDayOfMonth(4));

        System.out.println("修改年份：" + now.with(ChronoField.YEAR, 2024));
        System.out.println("修改月份：" + now.with(ChronoField.MONTH_OF_YEAR, 4));
        System.out.println("修改天月：" + now.with(ChronoField.DAY_OF_MONTH, 3));
        System.out.println("修改天周：" + now.with(ChronoField.DAY_OF_WEEK, 6));

        System.out.println("当前月份第一天：" + now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("当前月份最后一天：" + now.with(TemporalAdjusters.lastDayOfMonth()));

        LocalDateTime localDateTime = now.atTime(LocalTime.now());
        System.out.println("日期转日期时间：" + localDateTime);
    }

}
