package com.xinlang.zly_xyx.cat_file_server.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-08
 */
public class ImgUtil {



    /**
     * 检查是否为图片
     * @param suffix
     */
    public static void checkImgSuffix(String suffix) {
        if (".jpg".equals(suffix) || ".png".equals(suffix) || ".PNG".equals(suffix) || ".JPG".equals(suffix)) {
            return;
        }
        throw new IllegalArgumentException("不支持["+suffix+"]图片类型!");
    }

    /**
     * 保存在本地
     */
    public static String saveImg(MultipartFile multipartFile, String path,String address){
        try{
            java.io.File file = new java.io.File(path);
            if(file.exists()){
                return path;
            }
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            paintWaterMarkPhoto(path,address);
            return path;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加水印
     * @param path
     * @param address
     */
    private static void paintWaterMarkPhoto(String path,String address) {
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
            // 透明度
            float alpha = 0.7f;
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 设置颜色和画笔粗细
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(10));
            graphics2D.setFont(new Font("SimSun", Font.BOLD, 30));
            // 绘制图案或文字
            String dateStr = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss").format(new Date());
            if(address!= null && address.isEmpty()){
                graphics2D.drawString(address, 20, (srcImage.getHeight(null) - 60));
            }
            graphics2D.drawString(dateStr, 20, (srcImage.getHeight(null) - 20));
            graphics2D.dispose();
            os = new FileOutputStream(path);
            // 生成图片 (可设置 jpg或者png格式)
            int index = path.lastIndexOf(".");
            ImageIO.write(bufImage, path.substring(index+1), os);
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

    /**
     *  缩略图
     * @param path
     */
    public static void resize(String path,String folderMax,String folderMini,int miniWidth,int miniHeight) throws IOException{
        String miniPath =  path.replace(folderMax, folderMini);
        File minFile = new File(miniPath);
        if(!minFile.getParentFile().exists()){
            minFile.getParentFile().mkdirs();
        }
        Thumbnails.of(path).size(miniWidth, miniHeight).toFile(miniPath);
    }
}
