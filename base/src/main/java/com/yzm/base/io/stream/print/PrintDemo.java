package com.yzm.base.io.stream.print;


import java.io.*;

public class PrintDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
        demo02();
    }

    private static void demo01() {
        try {
            File file = new File(PARENT_PATH, "print_1.txt");
            File file2 = new File(PARENT_PATH, "print_1_copy.txt");
            FileInputStream fis = new FileInputStream(file);
            PrintStream ps = new PrintStream(new FileOutputStream(file2));

            byte[] bs = new byte[(int) file.length()];
            int len;
            while ((len = fis.read(bs)) != -1) {
                ps.write(bs, 0, len);
            }

            fis.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo02() {
        try {
            File file = new File(PARENT_PATH, "print_2.txt");
            File file2 = new File(PARENT_PATH, "print_2_copy.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(file2));

            String s;
            while ((s = br.readLine()) != null) {
                //PrintWriter的println方法 相当于
                //BufferedWriter 的write() + newLine()
                pw.println(s);
            }

            br.close();
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
