package com.yzm.base.java8.time;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

public class Date_Calendar_Demo {

    public static void main(String[] args) {
//        test01();
//        demo01();
//        demo02();
        demo03();
    }

    private static void test01() {
        Date date = new Date();
        System.out.println("date = " + date);

        Date date2 = new Date(System.currentTimeMillis() + 2000);
        System.out.println("date2 = " + date2);

        System.out.println(date.after(date2));
        System.out.println(date.before(date2));
        System.out.println(new Date().getTime() == System.currentTimeMillis());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
    }

    //jdk 1.8
    private static void test02() {
        Date from = Date.from(Instant.now());
        System.out.println("from = " + from);

        Date from2 = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("from2 = " + from2);

        Date from3 = Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        System.out.println("from3 = " + from3);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime = " + localDateTime);
    }

    private static void demo01() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar.getTime());

        System.out.println("年：" + calendar.get(Calendar.YEAR));
        System.out.println("月：" + calendar.get(Calendar.MONTH) + 1);
        System.out.println("日：" + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("分：" + calendar.get(Calendar.MINUTE));
        System.out.println("秒：" + calendar.get(Calendar.SECOND));

        System.out.println("星期：" + (calendar.get(Calendar.DAY_OF_WEEK) - 1));
    }

    private static void demo02() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis() + 30 * 60 * 1000));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2022, Calendar.JULY, 28, 12, 11, 20);

        System.out.println("calendar = " + calendar.getTime());
        System.out.println("calendar2 = " + calendar2.getTime());
        System.out.println(calendar.after(calendar2));
        System.out.println(calendar.before(calendar2));
    }

    private static void demo03() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("修改前：" + calendar.getTime());
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 10);
        System.out.println("修改后：" + calendar.getTime());

        calendar.set(2022, Calendar.NOVEMBER, 18, 12, 20, 30);
        System.out.println(calendar.getTime());

        calendar.add(Calendar.YEAR, 10);
        System.out.println(calendar.getTime());

        calendar.add(Calendar.MONTH, 2);
        System.out.println(calendar.getTime());
    }

}
