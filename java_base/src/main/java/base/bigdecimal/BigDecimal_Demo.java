package base.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimal_Demo {
    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }

    //加减乘除
    private static void test03() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        System.out.println(a.add(b));// 1.9
        System.out.println(a.subtract(b));// 0.1
        System.out.println(a.multiply(b));// 0.90
        //System.out.println(a.divide(b));// 无法除尽，抛出 ArithmeticException 异常
        //scale 表示要保留几位小数，roundingMode 代表保留规则
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP));// 1.11

        BigDecimal c = new BigDecimal("1.23454");
        BigDecimal d = new BigDecimal("1.23455");
        BigDecimal e = new BigDecimal("1.23456");
        System.out.println(c.setScale(4, RoundingMode.HALF_DOWN)); //1.2345
        System.out.println(d.setScale(4, RoundingMode.HALF_DOWN)); //1.2345
        System.out.println(e.setScale(4, RoundingMode.HALF_DOWN)); //1.2346
    }

    //创建并比较
    private static void test02() {
        //创建BigDecimal，主要方式两种，推荐第一种
        BigDecimal decimal1 = new BigDecimal("2.11");
        BigDecimal decimal2 = BigDecimal.valueOf(2.110d);
        BigDecimal decimal3 = new BigDecimal("2.110");
        System.out.println("decimal1 = " + decimal1);
        System.out.println("decimal2 = " + decimal2);
        System.out.println("decimal3 = " + decimal3);

        //BigDecimal等值比较应使用compareTo()方法，而不是equals()方法，equals方法会比较值和精度，compareTo会忽略精度
        System.out.println(decimal1.equals(decimal3)); //false
        System.out.println(decimal2.equals(decimal3)); //false
        System.out.println(decimal1.compareTo(decimal2)); //0
        System.out.println(decimal1.compareTo(decimal3)); //0
    }

    //浮点数没有办法用二进制精确表示，因此存在精度丢失的风险
    private static void test01() {
        float a = 2.0f - 1.9f;
        float b = 1.8f - 1.7f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999905
        System.out.println(a == b);// false
    }
}
