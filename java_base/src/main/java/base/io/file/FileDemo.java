package base.io.file;

import java.io.File;
import java.io.IOException;

/**
 * 文件
 */
public class FileDemo {

    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }


    private static void demo01() {
        File fileSrc = new File("C:\\a.txt\\2.txt.txt");
        File fileDest = new File("C:\\b\\2.txt.txt");
        System.out.println(fileSrc.renameTo(fileDest));
    }

    private static void demo02() {
        File file = new File("C:\\a.txt");
        File file2 = new File("C:\\a.txt\\1.txt");
        System.out.println(file.isFile());
        System.out.println(file2.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file2.isDirectory());
    }

    private static void demo03() {
        try {
            File file = File.createTempFile("temp", ".txt", new File("C:\\a.txt"));
            System.out.println(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
