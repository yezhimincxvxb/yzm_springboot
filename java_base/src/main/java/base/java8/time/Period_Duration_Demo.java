package base.java8.time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Period_Duration_Demo {
    public static void main(String[] args) {
//        test01();
//        test02();
//        demo01();
        demo02();
    }

    /**
     * Period此类以年、月和日为单位对时间量或时间量进行建模。
     * 我们可以用它来增减时间，或者计算两个时间间的时间差。
     */
    private static void test01() {
        Period period1 = Period.ofYears(2);
        Period period2 = Period.ofMonths(3);
        Period period3 = Period.ofWeeks(4);
        Period period4 = Period.ofDays(5);
        Period period5 = Period.of(1, 2, 3);
        System.out.println("period1 = " + period1);
        System.out.println("period2 = " + period2);
        System.out.println("period3 = " + period3);
        System.out.println("period4 = " + period4);
        System.out.println("period5 = " + period5);

        //ChronoUnit支持：YEARS、MONTHS、DAYS
        System.out.println("年：" + period5.get(ChronoUnit.YEARS));
        System.out.println("年：" + period5.getYears());
        System.out.println("月：" + period5.get(ChronoUnit.MONTHS));
        System.out.println("月：" + period5.getMonths());
        System.out.println("日：" + period5.get(ChronoUnit.DAYS));
        System.out.println("日：" + period5.getDays());

    }

    private static void test02() {
        Period period = Period.parse("P1Y2M3D");
        System.out.println("period = " + period);
        System.out.println("修改年：" + period.withYears(2));
        System.out.println("修改月：" + period.withMonths(5));
        System.out.println("修改日：" + period.withDays(8));

        System.out.println("加1年：" + period.plusYears(2));
        System.out.println("减2天：" + period.minusDays(2));
        System.out.println("一个日期加另一个日期：" + period.plus(Period.parse("P1Y2M3D")));

        System.out.println("两个日期相差多少：" + Period.between(LocalDate.now(), LocalDate.now().plusDays(40)));
    }

    /**
     * Duration可以表示的单位是天、小时、分、秒、毫秒、纳秒，其内部结果是通过秒、纳秒进行存储的，其他可表示的单位，都是通过这两个单位组合实现的。
     * 比如，一分钟等于 3600 秒，那内部存储就是 3600 秒 0 纳秒；1 毫秒等于 1000000 纳秒，内部存储就是 0 秒 1000000 纳秒。
     */
    private static void demo01() {
        //ofDays、ofHours、ofMinutes、ofSeconds、ofMillis、ofNanos
        Duration duration1 = Duration.ofDays(1);
        Duration duration2 = Duration.of(2, ChronoUnit.DAYS);
        Duration duration3 = Duration.parse("P2DT3H4M");
        System.out.println("duration1 = " + duration1);
        System.out.println("duration2 = " + duration2);
        System.out.println("duration3 = " + duration3);

        System.out.println("2天3小时4分钟转换成秒值：" + duration3.getSeconds());
        System.out.println("2天3小时4分钟转换成秒值：" + duration3.get(ChronoUnit.SECONDS));
        System.out.println("2天3小时4分钟转换成纳秒值：" + duration3.getNano());
        System.out.println("2天3小时4分钟转换成纳秒值：" + duration3.get(ChronoUnit.NANOS));

    }

    private static void demo02() {
        Duration duration3 = Duration.parse("P2DT3H4M30S");
        System.out.println("duration3 = " + duration3);

        System.out.println("加一天：" + duration3.plusDays(1));
        System.out.println("加一小时：" + duration3.plusHours(1));
        System.out.println("加一分钟：" + duration3.plusMinutes(1));
        System.out.println("加一秒：" + duration3.plus(1,ChronoUnit.SECONDS));
        System.out.println("减一秒：" + duration3.minus(1,ChronoUnit.SECONDS));

        System.out.println(duration3.toDays());
        System.out.println(duration3.toMinutes());
        System.out.println(duration3.toMillis());

        Duration between = Duration.between(LocalTime.parse("13:35:50"), LocalTime.of(8, 12, 30));
        System.out.println("between = " + between);
    }
}
