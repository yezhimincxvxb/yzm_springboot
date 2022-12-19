package base.java8.time;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime now1 = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime now3 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("now = " + now);
        System.out.println("now1 = " + now1);
        System.out.println("now2 = " + now2);
        System.out.println("now3 = " + now3);

        ZonedDateTime of = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        System.out.println("of = " + of);

        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(new Date().toInstant(), ZoneId.of("Asia/Shanghai"));
        System.out.println("ofInstant = " + ofInstant);

        ZonedDateTime parse = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        System.out.println("parse = " + parse);
    }
}
