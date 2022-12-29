package com.yzm.base.io.stream.buffered;

import java.io.*;

/**
 * 缓冲字节流
 * BufferedInputStream
 * BufferedOutputStream
 */
public class BufferedStreamDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        try {
            File file = new File(PARENT_PATH, "buffer_1.txt");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write("ABC".getBytes());
            bos.write("\r\n".getBytes());
            bos.write("998".getBytes());
            bos.flush();
            bos.close();

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[(int) file.length()];
            bis.read(b);
            bis.close();
            System.out.println(new String(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
