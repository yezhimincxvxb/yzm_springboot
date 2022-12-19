package base.io.stream.piped;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流：不同线程之间的相互通信
 */
public class PipedDemo {

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream();
            pos.connect(pis);

            Thread t1 = new Thread(() -> {
                System.out.println("线程：" + Thread.currentThread().getName() + "启动");
                try {
                    for (int i = 1; i <= 10; i++) {
                        pos.write(("线程：" + Thread.currentThread().getName() + ", 开始写入：" + i + "\n").getBytes());
                        Thread.sleep(1000);
                    }
                    pos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程：" + Thread.currentThread().getName() + "结束");
            }, "t1");

            Thread t2 = new Thread(() -> {
                System.out.println("线程：" + Thread.currentThread().getName() + "启动");
                try {
                    byte[] bs = new byte[1024];
                    int len;
                    while ((len = pis.read(bs)) != -1) {
                        System.out.println("线程：" + Thread.currentThread().getName() + ", 开始读取 ==> " + new String(bs, 0, len));
                    }
                    pis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("线程：" + Thread.currentThread().getName() + "结束");
            }, "t2");

            t1.start();
            t2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
