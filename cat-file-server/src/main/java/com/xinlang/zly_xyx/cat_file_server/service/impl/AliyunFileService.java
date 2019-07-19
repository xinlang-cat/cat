package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.aliyun.oss.OSSClient;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.bean.FileSource;
import com.xinlang.zly_xyx.cat_file_server.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * Aliyun stores files
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Service("aliyunFileService")
public class AliyunFileService extends AbstractFileService  {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private OSSClient ossClient;

    @Value("${file.aliyun.bucketName}")
    private String bucketName;
    @Value("${file.aliyun.domain}")
    private String domain;

    @Override
    protected FileMapper getFileMapper() {
        return fileMapper;
    }

    @Override
    protected FileSource source() {
        return FileSource.ALIYUN;
    }

    @Override
    protected void uploadFile(MultipartFile multipartFile, File file) throws Exception {
        ossClient.putObject(bucketName,file.getName(),multipartFile.getInputStream());
        file.setUrl(domain+"/"+file.getName());
    }

    @Override
    protected boolean delFile(File file) {
        ossClient.deleteObject(bucketName,file.getName());
        return true;
    }

}
