package com.xinlang.zly_xyx.cat_file_server.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-08
 */
public class ImgUtil {

    public static void checkImgSuffix(String suffix) {
        if (".jpg".equals(suffix) || ".png".equals(suffix) || ".PNG".equals(suffix) || ".JPG".equals(suffix)) {
            return;
        }
        throw new IllegalArgumentException("不支持["+suffix+"]图片类型!");
    }

    public static void paintWaterMarkPhoto(String path,String address) {
        Integer degree = 0;
        OutputStream os = null;
        try {
            Image srcImage = ImageIO.read(new File(path));
            BufferedImage bufImage = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 得到画布对象
            Graphics2D graphics2D = bufImage.createGraphics();
            // 设置对线段的锯齿状边缘处理
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(srcImage.getScaledInstance(srcImage.getWidth(null), srcImage.getHeight(null), Image.SCALE_SMOOTH),
                    0, 0, null);
            if (null != degree) {
                // 设置水印旋转角度及坐标
                graphics2D.rotate(Math.toRadians(degree), (double) bufImage.getWidth() / 2, (double) bufImage.getHeight() / 2);
            }
            // 透明度
            float alpha = 1f;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 设置颜色和画笔粗细
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(10));
            graphics2D.setFont(new Font("SimSun", Font.ITALIC, 18));
            // 绘制图案或文字
            String cont = "无名氏  lat:123.32323  wat:232323232";
            String dateStr = new SimpleDateFormat("YYYY-MM-dd hh-mm-ss").format(new Date());
            int charWidth1 = 8;
            int charWidth2 = 8;
            int halfGap = 12;
            graphics2D.drawString(cont, (srcImage.getWidth(null) - cont.length() * charWidth1) +20,
                    (srcImage.getHeight(null) - (charWidth1 + halfGap)) +20);
            graphics2D.drawString(dateStr, (srcImage.getWidth(null) - dateStr.length() * charWidth2) +20,
                    (srcImage.getHeight(null) + (charWidth2 + halfGap)) +20);
            graphics2D.dispose();
            os = new FileOutputStream(path);
            // 生成图片 (可设置 jpg或者png格式)
            ImageIO.write(bufImage, "png", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        paintWaterMarkPhoto("C:/Users/pc/Desktop/微信图片_20191108193548.jpg","南宁");
    }
}
