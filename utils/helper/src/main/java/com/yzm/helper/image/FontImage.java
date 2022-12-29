package com.yzm.helper.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 创建文字图片
 *
 * @author yuki_ho
 */
public class FontImage {

    private static final Logger log = LoggerFactory.getLogger(FontImage.class);

    // 默认格式
    private static final String FORMAT_NAME = "JPG";
    // 默认 宽度
    private static final int WIDTH = 100;
    // 默认 高度
    private static final int HEIGHT = 100;

    /**
     * 创建图片
     *
     * @param content 内容
     * @param font    字体
     * @param width   宽
     * @param height  高
     * @return
     */
    private static BufferedImage createImage(String content, Font font, Integer width, Integer height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.BLACK);

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        g2.drawString(content, (int) x, (int) baseY);

        return bi;
    }

    /**
     * 获取 图片
     *
     * @param content 内容
     * @param font    字体
     * @param width   宽
     * @param height  高
     * @return
     */
    public static BufferedImage getImage(String content, Font font, Integer width, Integer height) {
        width = width == null ? WIDTH : width;
        height = height == null ? HEIGHT : height;
        if (null == font)
            font = new Font("Serif", Font.BOLD, 11);
        return createImage(content, font, width, height);
    }

    /**
     * 获取 图片
     *
     * @param content 内容
     * @param width   宽
     * @param height  高
     * @return
     */
    public static BufferedImage getImage(String content, Integer width, Integer height) {

        return getImage(content, null, width, height);
    }

    /**
     * 获取图片
     *
     * @param content 内容
     * @return
     */
    public static BufferedImage getImage(String content) {

        return getImage(content, null, null);
    }

    /**
     * 获取图片
     *
     * @param content  内容
     * @param font     字体
     * @param width    宽
     * @param height   高
     * @param destPath 输出路径
     * @throws IOException
     */
    public static void getImage(String content, Font font, Integer width, Integer height, String destPath) throws IOException {
        mkdirs(destPath);
        String file = UUID.randomUUID().toString() + ".jpg";
        ImageIO.write(getImage(content, font, width, height), FORMAT_NAME, new File(destPath + "/" + file));
    }

    /**
     * 获取图片
     *
     * @param content 内容
     * @param font    字体
     * @param width   宽
     * @param height  高
     * @param output  输出流
     * @throws IOException
     */
    public static void getImage(String content, Font font, Integer width, Integer height, OutputStream output) throws IOException {
        ImageIO.write(getImage(content, font, width, height), FORMAT_NAME, output);
    }

    /**
     * 获取图片
     *
     * @param content  内容
     * @param width    宽
     * @param height   高
     * @param destPath 输出路径
     * @throws IOException
     */
    public static void getImage(String content, Integer width, Integer height, String destPath) throws IOException {
        getImage(content, null, width, height, destPath);
    }

    /**
     * 获取图片
     *
     * @param content 内容
     * @param width   宽
     * @param height  高
     * @param output  输出流
     * @throws IOException
     */
    public static void getImage(String content, Integer width, Integer height, OutputStream output) throws IOException {
        getImage(content, null, width, height, output);
    }


    /**
     * 创建 目录
     *
     * @param destPath
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

//    public static void main(String[] args) throws Exception {
//        getImage("MAS-123456、MAS-123456、MAS-123456、\r\nMAS-123456、MAS-123456、MAS-123456、\r\nMAS-123456、MAS-123456、MAS-123456、MAS-123456", 500, 300, "c:/test");
//
//    }

    public static void main(String[] args) throws Exception {
        String message = "“两国交兵，不斩来使”在后世流传下来的交战规则主要只有“两国交兵，不斩来使”。春秋时期诸侯派出的外交使节是不可侵犯的。公元前596年楚国派出申舟出使齐国，楚庄王特意嘱咐不要从宋国经过。宋国执政华元听说了，觉得这是对宋国的莫大侮辱，就设伏击杀死楚国使者。楚庄王为此“投袂而起”，出动大军包围宋国国都整整9个月。宋国派出使者到晋国告急，晋国上一年刚被楚军打败，不敢冒与楚国全面冲突的危险，只是派解扬为使者劝宋国坚守，不要投降。解扬经过郑国，被郑国抓起来交给楚国。楚庄王亲自接见解扬，企图买通他，要他向宋军喊话，说晋军不再提供救援，断绝宋军的希望，解扬不同意。经楚庄王几次威逼利诱，解扬才答应下来。可是当解扬来到了望城中的楼车上，就大声疾呼，说晋国援军不日就到，请宋国无论如何要坚持下去。楚庄王大怒，解扬说：“我答应你的条件只是为了实现使命，现在使命实现了，请立刻处死我。”楚庄王无话可说，反而释放他回晋国。长期围困而无战果，楚庄王打算退兵，可申舟的父亲拦在车前，说：“我儿子不惜生命以完成国王的使命，难道国王要食言了吗？”楚庄王无言以对。申舟父亲建议在宋国建造住房、耕种土地，表示要长期占领宋国，宋国就会表示屈服。宋国见楚军不肯撤退，就派华元为使者来谈判。华元半夜里潜入楚军大营，劫持了楚军统帅子反，说：“我的国君要我为使者来谈判，现在城内确实已是‘易子而食，析骸以爨’，但是如果订立城下之盟则情愿举国牺牲。贵军退到三十里外，我国唯命是听。”子反就在睡床上保证做到。第二天报告了楚庄王，楚军真的退30里外，和宋国停战，双方保证不再互相欺瞒，华元作为这项和约的人质到楚国居住。\n" +
                "后世将这一交战规则称之为“两国交兵，不斩来使”。历史上最著名的战时两国使节以礼相见的故事是“彭城相会”。450年南朝刘宋与北魏发生战争，刘宋发起北伐，先胜后败，战略据点彭城被包围。江夏王刘义恭率领军队死守彭城（今徐州），北魏太武帝想一举打过长江，派出李孝伯为使节进彭城劝降。刘义恭派了张畅为代表与李孝伯谈判。两人都是当时的“名士”，互相代表各自的君主赠送礼品，尽管处在极其残酷的战争环境，但他们在谈判中却仍然是文质彬彬、礼貌周全。这次谈判本身并没有什么实质性的结果，可双方的礼节及言辞，一直被后世誉为战场佳话。";
        String[] strArr = message.split("\n");
        int image_height = 700; // 每张图片的高度
        int line_height = 30; // 每行或者每个文字的高度
        int every_line = image_height / line_height; // 每张图片有多少行文字

        createImage(strArr, new Font("宋体", Font.PLAIN, 22), 350, image_height, every_line, line_height);

    }


    /**
     * 根据str,font的样式等生成图片
     * https://blog.csdn.net/sinat_28505133/article/details/54669111
     *
     * @param strArr
     * @param font
     * @param width
     * @param image_height
     * @throws Exception
     */
    public static void createImage(String[] strArr, Font font,
                                   int width, int image_height, int every_line, int line_height) throws Exception {

        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
        int stringWidth = fm.charWidth('字');// 标点符号也算一个字
        int line_string_num = width % stringWidth == 0 ? (width / stringWidth) : (width / stringWidth) + 1;
        System.out.println("每行=" + line_string_num);

        List<String> listStr = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        for (int h = 0; h < strArr.length; h++) {
            listStr.add(strArr[h]);
        }

        for (int j = 0; j < listStr.size(); j++) {
            if (listStr.get(j).length() > line_string_num) {
                newList.add(listStr.get(j).substring(0, line_string_num));
                listStr.add(j + 1, listStr.get(j).substring(line_string_num));
                listStr.set(j, listStr.get(j).substring(0, line_string_num));
            } else {
                newList.add(listStr.get(j));
            }
        }

        int a = newList.size();
        int b = every_line;
        int imgNum = a % b == 0 ? (a / b) : (a / b) + 1;

        for (int m = 0; m < imgNum; m++) {
            String filePath = "C:\\test\\" + System.currentTimeMillis() + ".jpg";
            File outFile = new File(filePath);
            if (!outFile.exists()) outFile.createNewFile();
            // 创建图片
            BufferedImage image = new BufferedImage(width, image_height,
                    BufferedImage.TYPE_INT_BGR);
            Graphics g = image.getGraphics();
            g.setClip(0, 0, width, image_height);
            g.setColor(Color.white); // 背景色白色
            g.fillRect(0, 0, width, image_height);
            g.setColor(Color.black);//  字体颜色黑色
            g.setFont(font);// 设置画笔字体
            // 每张多少行，当到最后一张时判断是否填充满
            for (int i = 0; i < every_line; i++) {
                int index = i + m * every_line;
                if (newList.size() - 1 >= index) {
                    System.out.println("每行实际=" + newList.get(index).length());
                    g.drawString(newList.get(index), 0, line_height * (i + 1));
                }
            }
            g.dispose();
            log.info("filepath={}", outFile.getAbsolutePath());
            ImageIO.write(image, "jpg", outFile);// 输出png图片
        }
    }
}