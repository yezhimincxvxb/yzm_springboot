package com.yzm.base.io.stream.data;

import java.io.*;

/**
 * DataInputStream 是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。
 * 应用程序可以使用DataOutputStream(数据输出流)写入由DataInputStream(数据输入流)读取的数据。
 */
public class DataStreamDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        try {
            File file = new File(PARENT_PATH, "data.txt");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            dos.writeInt(100);
            dos.writeUTF("DataOutputStream Test");
            dos.close();

            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            System.out.println("int:" + dis.readInt());
            System.out.println("UTF:" + dis.readUTF());
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
