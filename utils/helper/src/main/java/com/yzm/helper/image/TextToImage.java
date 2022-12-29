package com.yzm.helper.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 如果一行文字长度超过了既定的宽度，那么主动执行换行操作
 */
public class TextToImage {

    private static final Logger log = LoggerFactory.getLogger(TextToImage.class);

    /**
     * 图片保存地址
     */
    private static final String basePath = "c:\\test\\";
    /**
     * 图片宽度
     */
    private static final int width = 500;
    /**
     * 每一行的高度
     */
    private static final int line_height = 30;
    /**
     * 字体
     */
    private static final Font font = new Font("宋体", Font.PLAIN, 20);

    public static void createImage(String str) throws Exception {
        FontMetrics fm = FontDesignMetrics.getMetrics(font);
        int stringWidth = fm.charWidth('字');// 标点符号也算一个字
        //计算每行多少字 = 宽/每个字占用的宽度
        int line_string_num = width % stringWidth == 0 ? (width / stringWidth) : (width / stringWidth) + 1;
        System.out.println("每行字数=" + line_string_num);

        //将数组转为list
        List<String> strList = new ArrayList<>();
        //按照每行多少个字进行分割
        for (; ; ) {
            //当字数超过限制，就进行分割
            if (str.length() > line_string_num) {
                strList.add(str.substring(0, line_string_num));
                str = str.substring(line_string_num);
            } else {
                strList.add(str);
                break;
            }
        }

        //计算图片的高度，多预留一行
        int image_height = strList.size() * line_height + line_height;

        // 创建图片  宽度多预留一点
        BufferedImage image = new BufferedImage(width + 20, image_height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setFont(font);// 设置画笔字体
        g.setClip(0, 0, width + 20, image_height);
        g.setColor(Color.white); // 背景色白色
        g.fillRect(0, 0, width + 20, image_height);
        g.setColor(Color.black);//  字体颜色黑色

        for (int i = 0; i < strList.size(); i++) {
            g.drawString(strList.get(i), 0, line_height * (i + 1));
        }
        g.dispose();

        File outFile = new File(basePath + System.currentTimeMillis() + ".jpg");
        log.info("filepath={}", outFile.getAbsolutePath());
        ImageIO.write(image, "jpg", outFile);// 输出图片
    }

    public static void main(String[] args) throws Exception {
        String message =
                "发布Blink，回答问题：得分，这些活动每次得分 1 分， 每天最多 2 分左右。 另外请注意，发布资源，如果资源没有得到好评，那是没有分数的，甚至要倒扣分数。 \n" +
                        "注意：在问答栏目提问没有分数\n" +
                        "内容获得反馈 （各个单项每天的得分有上限，每天最多不超过 10 分）\n" +
                        "被评论，点赞，评价，被关注，资源被评价（分数大于 3 分）\n" +
                        "提问没有分数，但是提的问题被关注，也有分数\n" +
                        "被收藏，订阅，下载，下订单，回答被采纳\n" +
                        "认证：职业认证，企业认证，专家认证 （一次性的得分 100 - 500 不等）\n" +
                        "创作者收获粉丝，也会获得原力值。但是这些粉丝要符合基本条件（一年内有原创的博客/帖子，粉丝自己要有粉丝），我们要避免水军和低质量的粉丝降低原力值的含金量。 ";
        createImage(message);
    }


}


