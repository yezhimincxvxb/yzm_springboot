package com.yzm.base.io.stream.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 文件字符流
 * FileReader
 * FileWriter
 */
public class FileDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        try {
            File file = new File(PARENT_PATH, "file_2.txt");
            FileWriter fw = new FileWriter(file);

            fw.write(97);
            char[] c = new char[]{'b', 'c', 'd'};
            fw.write(c);
            fw.write("\r\n");
            fw.write("efg\r\nhij", 0, 5);
            fw.append('h').append("ijk");
            fw.close();

            FileReader fr = new FileReader(file);
            char[] c2 = new char[(int) file.length()];
            fr.read(c2);
            fr.close();
            System.out.println(new String(c2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
