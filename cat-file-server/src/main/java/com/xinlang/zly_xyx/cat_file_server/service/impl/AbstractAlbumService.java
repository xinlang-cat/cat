package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.bean.Source;
import com.xinlang.zly_xyx.cat_file_server.mapper.AlbumMapper;
import com.xinlang.zly_xyx.cat_file_server.mapper.FileMapper;
import com.xinlang.zly_xyx.cat_file_server.service.IAlbumService;
import com.xinlang.zly_xyx.cat_file_server.service.IFileService;
import com.xinlang.zly_xyx.cat_file_server.utils.AlbumUtil;
import com.xinlang.zly_xyx.cat_file_server.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Slf4j
public abstract class AbstractAlbumService implements IAlbumService {

    protected abstract AlbumMapper getAlbumMapper();

    protected abstract Source source();

    protected abstract void uploadAlbum(MultipartFile multipartFile,Album album,String address )throws Exception;

    protected abstract boolean delAlbum(Album album);

    /**
     *
     * upload files
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @Override
    public Album upload(HttpServletRequest request,MultipartFile multipartFile, String address){
        try {
            Album album = AlbumUtil.getAlbum(multipartFile,request);
            Album oldAlbum = getAlbumMapper().getById(album.getId());
            //避免重复上传
            if(oldAlbum != null){
                return oldAlbum;
            }
            if(!album.getName().contains(".")){
                throw new IllegalArgumentException("缺少后缀名" );
            }
            uploadAlbum(multipartFile,album,address);
            //设置文件来源
            album.setSource(source().name());
            //将文件信息保存到数据库
            getAlbumMapper().save(album);
            log.info("上传文件:{}",album);
            return album;
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage().length()>50?"图片上传失败!":e.getMessage());
        }
    }

    /**
     *
     * @param album
     */
    @Override
    public void del(Album album){
        delAlbum(album);
        getAlbumMapper().delete(album.getId());
        log.info("删除文件：{}",album);
    }
}
