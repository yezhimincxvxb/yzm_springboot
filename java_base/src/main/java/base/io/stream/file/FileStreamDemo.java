package base.io.stream.file;

import java.io.*;

/**
 * 文件字节流
 * FileOutputStream
 * FileInputStream
 */
public class FileStreamDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();
    }

    /**
     * 文件字节写入
     * FileOutputStream（File file，boolean append）
     * file不存在则自动创建
     * append为true则每次写入都是末尾拼接，不会覆盖
     * append为false(默认)则每次写入都是覆盖原来的
     */
    private static void demo01() {
        OutputStream fos;
        try {
            fos = new FileOutputStream(new File(PARENT_PATH, "file_1.txt"));
            fos.write("姓名：张三".getBytes());
            fos.write("\r\n".getBytes());
            fos.write("年龄：18".getBytes());
            System.out.println("写入完成");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件字节读取
     * FileInputStream(File file)
     * file不存在会报错
     */
    private static void demo02() {
        InputStream fis;
        try {
            File file = new File(PARENT_PATH, "file_1.txt");
            fis = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            fis.read(b);
            System.out.println(new String(b));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从1.txt读取数据保存到1_copy.txt
     */
    private static void demo03() {
        InputStream fis;
        OutputStream fos;
        try {
            File fileSrc = new File(PARENT_PATH, "file_1.txt");
            File fileDest = new File(PARENT_PATH, "file_1_copy.txt");
            fis = new FileInputStream(fileSrc);
            fos = new FileOutputStream(fileDest);

            byte[] b = new byte[(int) fileSrc.length()];
            while (fis.read(b) != -1) {
                fos.write(b);
            }

            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
