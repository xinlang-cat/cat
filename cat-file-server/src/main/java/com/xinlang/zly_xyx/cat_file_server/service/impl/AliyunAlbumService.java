package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.aliyun.oss.OSSClient;
import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.bean.Source;
import com.xinlang.zly_xyx.cat_file_server.mapper.AlbumMapper;
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
@Service("aliyunAlbumService")
public class AliyunAlbumService extends AbstractAlbumService  {

    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private OSSClient ossClient;

    @Value("${file.aliyun.bucketName}")
    private String bucketName;
    @Value("${file.aliyun.domain}")
    private String domain;

    @Override
    protected AlbumMapper getAlbumMapper() {
        return albumMapper;
    }

    @Override
    protected Source source() {
        return Source.ALIYUN;
    }

    @Override
    protected void uploadAlbum(MultipartFile multipartFile, Album album) throws Exception {
        ossClient.putObject(bucketName,album.getName(),multipartFile.getInputStream());
        album.setUrl(domain+"/"+album.getName());
    }

    @Override
    protected boolean delAlbum(Album album) {
        ossClient.deleteObject(bucketName,album.getName());
        return true;
    }

}
