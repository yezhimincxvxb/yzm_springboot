package com.yzm.base.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BigDecimalDemo {
    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
//        test04();
        test05();
    }

    private static void test05() {
        double pi = 3.1415927;
        //取整数部分
        System.out.println(new DecimalFormat("0").format(pi));//3
        System.out.println(new DecimalFormat("#").format(pi));//3
        //保留两位小数
        System.out.println(new DecimalFormat("0.00").format(pi));//3.14
        System.out.println(new DecimalFormat("#.00").format(pi));//3.14
        //取两位整数和三位小数
        System.out.println(new DecimalFormat("00.000").format(pi));// 03.142
        System.out.println(new DecimalFormat("##.000").format(pi));// 3.142
        //以百分比方式计数，并取两位小数
        System.out.println(new DecimalFormat("0.00%").format(pi));//314.16%
        System.out.println(new DecimalFormat("#.##%").format(pi));//314.16%
        long c = 299792458;//光速
        //显示为科学计数法，并取五位小数
        System.out.println(new DecimalFormat("#.#####E0").format(c));//2.99792E8
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println(new DecimalFormat("00.####E0").format(c));//29.9792E7
        //每三位以逗号进行分隔。
        System.out.println(new DecimalFormat(",###").format(c));//299,792,458
        //将格式嵌入文本
        System.out.println(new DecimalFormat("光速大小为每秒,###米。").format(c));

        // 总结:DecimalFormat 类主要靠 # 和 0 两种占位符号来指定数字长度。区别：0 表示如果位数不足则以 0 填充，#不会
    }

    private static void test04() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance(); //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("0.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("贷款金额:" + currency.format(loanAmount));
        System.out.println("利率:" + percent.format(interestRate));
        System.out.println("利息:" + currency.format(interest));
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
        //创建BigDecimal
        BigDecimal decimal0 = new BigDecimal(0.11);
        BigDecimal decimal1 = new BigDecimal("0.11");
        BigDecimal decimal2 = BigDecimal.valueOf(0.110);
        BigDecimal decimal3 = new BigDecimal("0.110");
        System.out.println("decimal0 = " + decimal0);
        System.out.println("decimal1 = " + decimal1);
        System.out.println("decimal2 = " + decimal2);
        System.out.println("decimal3 = " + decimal3);

        //BigDecimal等值比较应使用compareTo()方法，而不是equals()方法，equals方法会比较值和精度，compareTo会忽略精度
        System.out.println(decimal1.equals(decimal3)); //false
        System.out.println(decimal2.equals(decimal3)); //false
        System.out.println(decimal1.compareTo(decimal2)); //0
        System.out.println(decimal1.compareTo(decimal3)); //0
    }

    //浮点数没有办法用二进制精确表示，存在精度丢失的风险
    private static void test01() {
        float a = 2.0f - 1.9f;
        float b = 1.8f - 1.7f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999905
        System.out.println(a == b);// false
    }
}
