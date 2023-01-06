package com.yzm.base.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }

    private static void demo03() {
        System.out.println("生成固定长度，包含字母、数字的随机数：" + RandomStringUtils.random(15, true, true));
        System.out.println("根据给定的字符串生成固定长度的随机数：" + RandomStringUtils.random(15, "abcdefgABCDEFG123456789"));
        System.out.println("生成固定长度，只包含字母的随机数：" + RandomStringUtils.randomAlphabetic(15));
        System.out.println("生成指定长度，只包含字母的随机数：" + RandomStringUtils.randomAlphabetic(5, 15));
        System.out.println("生成固定长度，只包含数字的随机数：" + RandomStringUtils.randomNumeric(15));
        System.out.println("生成指定长度，只包含数字的随机数：" + RandomStringUtils.randomNumeric(5, 15));
        System.out.println("生成固定长度，包含数字和字母的随机数：" + RandomStringUtils.randomAlphanumeric(15));
        System.out.println("生成指定长度，包含数字和字母的随机数：" + RandomStringUtils.randomAlphanumeric(5, 15));
    }

    private static void demo02() {
        System.out.println("生成一个随机的布尔值：" + RandomUtils.nextBoolean());
        System.out.println("生成一个随机int类型数：" + RandomUtils.nextInt());
        System.out.println("生成一个指定区间的随机int类型数(包头不包尾)：" + RandomUtils.nextInt(200, 600));
        System.out.println("生成一个随机long类型数：" + RandomUtils.nextLong());
        System.out.println("生成一个指定区间的随机long类型数(包头不包尾)：" + RandomUtils.nextLong(200, 600));
        System.out.println("生成一个随机float类型数：" + RandomUtils.nextFloat());
        System.out.println("生成一个指定区间的随机float类型数(包头不包尾)：" + RandomUtils.nextFloat(200, 600));
        System.out.println("生成一个随机double类型数：" + RandomUtils.nextDouble());
        System.out.println("生成一个指定区间的随机double类型数(包头不包尾)：" + RandomUtils.nextDouble(200, 600));
    }

    private static void demo01() {
        System.out.println("0~10的随机数：");
        for (int i = 0; i < 20; i++) {
            System.out.print(new Random().nextInt(11) + "\t");
        }
        System.out.println();

        System.out.println("50~100的随机数：");
        for (int i = 0; i < 20; i++) {
            System.out.print((50 + new Random().nextInt(51)) + "\t");
        }
        System.out.println();
    }
}
