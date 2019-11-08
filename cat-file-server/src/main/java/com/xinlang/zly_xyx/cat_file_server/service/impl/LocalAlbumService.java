package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.Source;
import com.xinlang.zly_xyx.cat_file_server.mapper.AlbumMapper;
import com.xinlang.zly_xyx.cat_file_server.utils.FileUtil;
import com.xinlang.zly_xyx.cat_file_server.utils.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * local store file
 * this implementation file service can only deploy one
 * if multiple machines can be Shared to a directory, you can deploy multiple machines
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Service("localAlbumService")
public class LocalAlbumService extends AbstractAlbumService{

    @Autowired
    private AlbumMapper albumMapper;

    @Value("${file.local.urlPrefix}")
    private String urlPrefix;
    /**
     * The upload file is stored in the local root path
     */
    @Value("${file.local.path}")
    private String localAlbumPath;

    @Override
    protected AlbumMapper getAlbumMapper() {
        return albumMapper;
    }

    @Override
    protected Source source() {
        return Source.LOCAL;
    }

    @Override
    protected void uploadAlbum(MultipartFile multipartFile, Album album) throws Exception {
        int index = album.getName().lastIndexOf(".");
        String albumSuffix = album.getName().substring(index);
        ImgUtil.checkImgSuffix(albumSuffix);
        String suffix = "/"+ LocalDate.now().toString().replace("-","/")+"/"+album.getId()+albumSuffix;
        String path = localAlbumPath+suffix;
        String url = urlPrefix+suffix;
        album.setPath(path);
        album.setUrl(url);
        FileUtil.saveFile(multipartFile,path);
    }

    @Override
    protected boolean delAlbum(Album album) {
        return false;
    }
}
