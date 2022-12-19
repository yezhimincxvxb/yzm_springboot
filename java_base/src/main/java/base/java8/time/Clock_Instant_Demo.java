package base.java8.time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class Clock_Instant_Demo {
    public static void main(String[] args) {
//        test01();
//        demo01();
//        demo02();
        demo03();
    }

    /**
     * 使用时区提供对当前时刻、日期和时间的访问的时钟。
     */
    private static void test01() {
        Clock clock1 = Clock.systemDefaultZone();
        Clock clock2 = Clock.system(ZoneId.systemDefault());
        Clock clock3 = Clock.systemUTC();
        Clock clock4 = Clock.system(ZoneId.of("Asia/Shanghai"));
        Clock clock5 = Clock.system(ZoneId.of("America/New_York"));
        System.out.println("clock1 = " + clock1);
        System.out.println("clock2 = " + clock2);
        System.out.println("clock3 = " + clock3);
        System.out.println("clock4 = " + clock4);
        System.out.println("clock5 = " + clock5);

        System.out.println("获取毫秒值：" + clock1.millis());
        System.out.println("获取毫秒值：" + clock2.millis());
        System.out.println("获取毫秒值：" + clock3.millis());
        System.out.println("获取毫秒值：" + clock4.millis());
        System.out.println("获取毫秒值：" + clock5.millis());
        System.out.println("获取毫秒值：" + System.currentTimeMillis());

        System.out.println("时区：" + clock1.getZone().getId());
        System.out.println("时区：" + clock2.getZone().getId());
        System.out.println("时区：" + clock3.getZone().getId());
        System.out.println("时区：" + clock4.getZone().getId());
        System.out.println("时区：" + clock5.getZone().getId());
    }

    private static void demo03() {
        Instant now = Instant.now();
        Instant parse = Instant.parse("2022-08-28T06:20:39.867Z");

        //早晚
        System.out.println(now.isAfter(parse));
        System.out.println(now.isBefore(parse));

        System.out.println("相差天数 = " + now.until(parse, ChronoUnit.DAYS));
        System.out.println("相差小时 = " + now.until(parse, ChronoUnit.HOURS));
        System.out.println("相差分钟 = " + now.until(parse, ChronoUnit.MINUTES));
        System.out.println("相差毫秒数 = " + now.until(parse, ChronoUnit.MILLIS));
    }

    private static void demo02() {
        Instant now = Instant.now();
        //增减
        System.out.println("将8小时转换成秒值表示：" + TimeUnit.HOURS.toMillis(8));
        System.out.println("世界标准时间 = " + now);
        System.out.println("标准+8小时 = " + now.plusMillis(TimeUnit.HOURS.toMillis(8)));
        System.out.println("标准+8小时 = " + now.plusSeconds(TimeUnit.HOURS.toSeconds(8)));
        System.out.println("标准-8小时 = " + now.minusMillis(TimeUnit.HOURS.toMillis(8)));
        System.out.println("标准-8小时 = " + now.minusSeconds(TimeUnit.HOURS.toSeconds(8)));

        //ChronoUnit支持：NANOS、MICROS、MILLIS、SECONDS、MINUTES、HOURS、HALF_DAYS、DAYS
        System.out.println("标准+8小时 = " + now.plus(TimeUnit.HOURS.toMillis(8), ChronoUnit.MILLIS));
        System.out.println("标准+8小时 = " + now.plus(TimeUnit.HOURS.toHours(8), ChronoUnit.HOURS));
        System.out.println("标准+8天 = " + now.plus(TimeUnit.DAYS.toDays(8), ChronoUnit.DAYS));
    }

    /**
     * 时间线上的一个瞬间点
     */
    private static void demo01() {
        Instant instant1 = Instant.now();
        Instant instant2 = Clock.systemDefaultZone().instant();
        Instant instant3 = Clock.systemUTC().instant();
        Instant instant4 = Instant.ofEpochMilli(System.currentTimeMillis());
        Instant instant5 = Instant.parse("2022-12-03T10:15:30.677Z");
        System.out.println("instant1 = " + instant1);
        System.out.println("instant2 = " + instant2);
        System.out.println("instant3 = " + instant3);
        System.out.println("instant4 = " + instant4);
        System.out.println("instant5 = " + instant5);

        System.out.println("秒" + instant1.getEpochSecond()); //距离格林威治时间的秒数
        System.out.println("秒：" + instant1.getLong(ChronoField.INSTANT_SECONDS));
        System.out.println("毫秒" + instant1.toEpochMilli());  //距离格林威治时间的毫秒数

        //ChronoField支持：NANO_OF_SECOND、MICRO_OF_SECOND、MILLI_OF_SECOND、INSTANT_SECONDS
        System.out.println("纳秒：" + instant1.getLong(ChronoField.NANO_OF_SECOND));
        System.out.println("微秒：" + instant1.getLong(ChronoField.MICRO_OF_SECOND));
        System.out.println("毫秒：" + instant1.getLong(ChronoField.MILLI_OF_SECOND));
    }
}
