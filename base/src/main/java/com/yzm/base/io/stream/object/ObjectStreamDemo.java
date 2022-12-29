package com.yzm.base.io.stream.object;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 对象流
 * ObjectInputStream
 * ObjectOutputStream
 */
public class ObjectStreamDemo {

    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";

    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }

    private static void demo01() {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(new File(PARENT_PATH, "object_1.txt"));
            oos = new ObjectOutputStream(fos);

            ObjectDemo demo = new ObjectDemo();
            demo.setId(1);
            demo.setUsername("admin");
            demo.setPassword("123456");

            oos.writeObject(demo);

            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo02() {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(new File(PARENT_PATH, "object_1.txt"));
            ois = new ObjectInputStream(fis);

            ObjectDemo demo = (ObjectDemo) ois.readObject();
            System.out.println("demo = " + demo);

            fis.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo03() {
        ObjectOutputStream oos;
        ObjectInputStream ois;
        try {
            File file = new File(PARENT_PATH, "object_2.txt");
            oos = new ObjectOutputStream(new FileOutputStream(file));
            ois = new ObjectInputStream(new FileInputStream(file));


            List<ObjectDemo> list = Arrays.asList(
                    new ObjectDemo(1, "a.txt", "a.txt"),
                    new ObjectDemo(2, "b", "b"),
                    new ObjectDemo(3, "c", "c")
            );

            oos.writeObject(list);
            List<ObjectDemo> demoList = (List<ObjectDemo>) ois.readObject();
            demoList.forEach(System.out::println);

            oos.close();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
