package com.yzm.base.io.stream.buffered;

import java.io.*;

/**
 * 缓冲字符流
 * BufferedWriter
 * BufferedReader
 */
public class BufferedDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        try {
            File file = new File(PARENT_PATH, "buffer_2.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("abc");
            bw.newLine();
            bw.write("666");
            bw.newLine();
            bw.append("@&&").append("\r\n").append("==>");
            bw.flush();
            bw.close();

            BufferedReader br = new BufferedReader(new FileReader(file));
            char[] b = new char[(int) file.length()];
            br.read(b);
            br.close();
            System.out.println(new String(b));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
