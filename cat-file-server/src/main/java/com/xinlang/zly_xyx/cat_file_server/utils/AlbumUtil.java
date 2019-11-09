package com.xinlang.zly_xyx.cat_file_server.utils;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
public class
AlbumUtil {
    public static Album getAlbum(MultipartFile multipartFile, HttpServletRequest request) throws Exception{
        String md5 = FileUtil.fileMd5(multipartFile.getInputStream());
        String name = multipartFile.getOriginalFilename();
        int index = name.lastIndexOf(".");
        String albumSuffix = name.substring(index);
        ImgUtil.checkImgSuffix(albumSuffix);
        Album album =  new Album();
        album.setId(md5);
        album.setContentType(multipartFile.getContentType());
        album.setName(name);
        album.setUserId(AppUserUtil.getLoginAppUser().getId().intValue());
        album.setSize(multipartFile.getSize());
        album.setCreateTime(new Date());
        album.setIsWeChat(isWeChat(request));
        return album;
    }

    /**
     * 是否是微信
     *
     * @param request
     * @return
     */
    public static boolean isWeChat(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        return userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true;
    }


}


