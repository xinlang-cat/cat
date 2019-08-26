package com.xinlang.zly_xyx.cat_file_server.utils;

import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
public class
FileUtil {

    public static File getFile(MultipartFile multipartFile) throws Exception{
        String md5 = fileMd5(multipartFile.getInputStream());
        File file =  new File();
        file.setId(md5);
        file.setContentType(multipartFile.getContentType());
        file.setName(multipartFile.getOriginalFilename());
        file.setIsImg(multipartFile.getContentType().startsWith("image/"));
        file.setSize(multipartFile.getSize());
        file.setCreateTime(new Date());
        return file;
    }


    /**
     * 保存在本地
     */
    public static String saveFile(MultipartFile multipartFile,String path){
        try{
            java.io.File file = new java.io.File(path);
            if(file.exists()){
                return path;
            }
            if(file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            return path;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除本地文件
     */
    public static boolean delFile(String path){
        java.io.File file = new java.io.File(path);
        if(file.exists()) {
            boolean flag = file.delete();
            if (flag) {
                java.io.File[] files = file.getParentFile().listFiles();
                if (files == null || files.length == 0) {
                    file.getParentFile().delete();
                }
            }
            return flag;
        }
        return false;
    }

    /**
     * 文件MD5加密
     */
    public static String fileMd5(InputStream inputStream)  {
       try{
           return DigestUtils.md5Hex(inputStream);
       }catch (IOException e){
           e.printStackTrace();
       }
       return null;
    }
}


