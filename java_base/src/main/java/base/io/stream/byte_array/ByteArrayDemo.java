package base.io.stream.byte_array;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 * 字节数组流
 * ByteArrayOutputStream
 * ByteArrayInputStream
 */
public class ByteArrayDemo {

    private static final int LEN = 5;
    private static final String PARENT_PATH = System.getProperty("user.dir") + "\\io" + "\\src\\main\\resources\\stream";
    //对应英文字母“abcdefghijklmnopqrstuvwxyz”
    private static final byte[] src = new byte[]{
            97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122
    };

    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03_3();
    }

    private static void demo01() {
        try {
            //创建ByteArrayInputStream流，元素是src字节数组
            ByteArrayInputStream bai = new ByteArrayInputStream(src);
            //读取5个字节
            for (int i = 0; i < LEN; i++) {
                //判断当前位置pos是否有字节
                if (bai.available() > 0) {
                    //读取当前pos位置的字节，并pos+1
                    int read = bai.read();
                    System.out.println(read + " ==> " + (char) read);
                }
            }

            // 若不支持标记功能，则直接退出
            if (!bai.markSupported()) {
                System.out.println("make not supported!");
                return;
            }

            // 前面已经读取了5个字节，此时当前位置pos为5，指向102
            // mark()方法的参数无实际意义，调用mark()表示标记位置markPos，使用reset()可以回到该标记位置markPos
            // mark()与reset()是配套的，reset()会将“字符流中下一个被读取的位置”重置为“mark()中所保存的位置”
            bai.mark(0);

            // 跳过5个字节。此时当前位置pos为10，指向106
            bai.skip(5);

            // 读取5个字符。即读取“107, 108, 109, 110, 111”，
            byte[] buf = new byte[LEN];
            bai.read(buf, 0, LEN);
            System.out.printf("buf=%s\n", Arrays.toString(buf));

            // 重置：即，将“字节流中下一个被读取的位置”重置到“mark()所标记的位置”，即102。
            bai.reset();
            // 从“重置后的字符流”中读取5个字节到buf中。即读取“102, 103, 104, 105, 106”
            bai.read(buf);
            System.out.printf("buf=%s\n", Arrays.toString(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo02() {
        try {
            // 创建ByteArrayOutputStream字节流
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // 写入“a.txt”的字节
            bao.write(97);
            bao.write(src, 1, 5);
            System.out.printf("bao=%s\n", bao);

            // 计算长度
            System.out.printf("size=%s\n", bao.size());

            // 转换成byte[]数组
            byte[] buf = bao.toByteArray();
            System.out.printf("buf=%s\n",  Arrays.toString(buf));

            // 将bao写入到另一个输出流中
            ByteArrayOutputStream bao2 = new ByteArrayOutputStream();
            bao.writeTo(bao2);
            System.out.printf("bao2=%s\n", bao2);

            //清空流数据
            bao.reset();
            System.out.printf("size=%s\n", bao.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demo03_1() {
        FileOutputStream fos;
        ByteArrayInputStream bai;
        try {
            fos = new FileOutputStream(new File(PARENT_PATH, "array_1.txt"));
            bai = new ByteArrayInputStream("北京欢迎你".getBytes());

            int i;
            while ((i = bai.read()) != -1) {
                fos.write(i);
            }

            bai.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void demo03_2() {
        FileInputStream fis;
        ByteArrayOutputStream bao;
        try {
            fis = new FileInputStream(new File(PARENT_PATH, "array_1.txt"));
            bao = new ByteArrayOutputStream();

            int i;
            while ((i = fis.read()) != -1) {
                bao.write(i);
            }

            System.out.println("读取内容 ==> \r\n" + bao.toString());
            fis.close();
            bao.close();
            //bao关闭流后，依旧可以调用方法
            System.out.println("读取内容 ==> \r\n" + bao.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取网络图片
     */
    private static void demo03_3() {
        InputStream is;
        OutputStream os;
        ByteArrayOutputStream bos;
        try {
            //获取连接网络的输入流
            URL url = new URL("http://www.51gjie.com/Images/image1/lkqixikw.lqs.jpg");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            is = conn.getInputStream();

            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            byte[] imageData = bos.toByteArray();

            //输出
            File imageFile = new File(PARENT_PATH, "byte_image.jpg");
            os = new FileOutputStream(imageFile);
            os.write(imageData);

            is.close();
            bos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
