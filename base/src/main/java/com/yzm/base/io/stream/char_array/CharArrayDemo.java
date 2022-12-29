package com.yzm.base.io.stream.char_array;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * 字符数组流
 * CharArrayReader
 * CharArrayWriter
 */
public class CharArrayDemo {

    private static final int LEN = 5;
    // 对应英文字母“abcdefghijklmnopqrstuvwxyz”
    private static final char[] src = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
//        demo01();
        demo02();
    }

    /**
     * CharArrayReader的API测试函数
     */
    private static void demo01() {
        try {
            // 创建CharArrayReader字符流，内容是src数组
            CharArrayReader car = new CharArrayReader(src);

            // 遍历读取5个字符
            for (int i = 0; i < LEN; i++) {
                // 当前位置pos是否有字符
                if (car.ready()) {
                    // 读取字符后，指向下一个字符
                    char tmp = (char) car.read();
                    // 依次读取到a、b、c、d、e
                    System.out.printf("%d : %c\n", i, tmp);
                }
            }

            // 若“该字符流”不支持标记功能，则直接退出
            if (!car.markSupported()) {
                System.out.println("make not supported!");
                return;
            }

            // 前面已经读取了5个字符，此时当前位置pos为5，指向f
            // mark()方法的参数无实际意义，调用mark()表示标记位置markPos，使用reset()可以回到该标记位置markPos
            // mark()与reset()是配套的，reset()会将“字符流中下一个被读取的位置”重置为“mark()中所保存的位置”
            car.mark(0);

            // 跳过5个字符。此时当前位置pos为10，指向k
            car.skip(5);

            // 读取5个字符。即读取“klmno”，
            char[] buf = new char[LEN];
            car.read(buf, 0, LEN);
            System.out.printf("buf=%s\n", String.valueOf(buf));

            // 重置：即，将“字符流中下一个被读取的位置”重置到“mark()所标记的位置”，即f。
            car.reset();
            // 从“重置后的字符流”中读取5个字符到buf中。即读取“fghij”
            car.read(buf);
            System.out.printf("buf=%s\n", String.valueOf(buf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * CharArrayWriter的API测试函数
     */
    private static void demo02() {
        try {
            // 创建CharArrayWriter字符流
            CharArrayWriter caw = new CharArrayWriter();

            // 写入“A”个字符
            caw.write('A');
            // 写入字符串“BC”个字符
            caw.write("BC");
            System.out.printf("caw=%s\n", caw);

            // 将ArrayLetters数组中从“3”开始的后5个字符(defgh)写入到caw中。
            caw.write(src, 3, 5);
            System.out.printf("caw=%s\n", caw);

            // (01) 写入字符0
            // (02) 然后接着写入“123456789”
            // (03) 再接着写入ArrayLetters中第8-12个字符(ijkl)
            caw.append('0').append("123456789").append(String.valueOf(src), 8, 12);
            System.out.printf("caw=%s\n", caw);

            // 计算长度
            System.out.printf("size=%s\n", caw.size());

            // 转换成char[]数组
            char[] buf = caw.toCharArray();
            System.out.printf("buf=%s\n", String.valueOf(buf));

            // 将caw写入到另一个输出流中
            CharArrayWriter caw2 = new CharArrayWriter();
            caw.writeTo(caw2);
            System.out.printf("caw2=%s\n", caw2);

            //清空流数据
            caw.reset();
            System.out.printf("size=%s\n", caw.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
