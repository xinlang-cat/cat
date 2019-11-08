package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.Source;
import com.xinlang.zly_xyx.cat_file_server.mapper.AlbumMapper;
import com.xinlang.zly_xyx.cat_file_server.utils.FileUtil;
import com.xinlang.zly_xyx.cat_file_server.utils.ImgUtil;
import com.xinlang.zly_xyx.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Value("${album.local.urlPrefix}")
    private String urlPrefix;
    @Value("${album.local.path}")
    private String localAlbumPath;

    @Value("${album.local.folder.maxImg}")
    private String  folderMax;
    @Value("${album.local.mini.width}")
    private int miniWidth;
    @Value("${album.local.mini.height}")
    private int miniHeight;
    @Value("${album.local.folder.miniImg}")
    private String  folderMini;

    @Override
    protected AlbumMapper getAlbumMapper() {
        return albumMapper;
    }

    @Override
    protected Source source() {
        return Source.LOCAL;
    }

    @Override
    protected void uploadAlbum(MultipartFile multipartFile, Album album,String address) throws IOException {
        int index = album.getName().lastIndexOf(".");
        String albumSuffix = album.getName().substring(index);
        ImgUtil.checkImgSuffix(albumSuffix);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/")
                .append(AppUserUtil.getLoginAppUser().getUsername())
                .append("/")
                .append(folderMax)
                .append("/")
                .append(LocalDate.now().toString().replace("-","/"))
                .append(album.getId())
                .append(albumSuffix);
        String suffix = stringBuilder.toString();
        String path = localAlbumPath+suffix;
        String url = urlPrefix+suffix;
        album.setPath(path);
        album.setUrl(url);
        ImgUtil.saveImg(multipartFile,path,address);
        ImgUtil.resize(path,folderMax,folderMini,miniWidth,miniHeight);
    }

    @Override
    protected boolean delAlbum(Album album) {
        File file = new File(album.getPath());
        String miniPath =  album.getPath().replace(folderMax, folderMini);
        File file1 = new File(miniPath);
        file.delete();
        file1.delete();
        return false;
    }
}
