package com.xinlang.zly_xyx.cat_file_server.service;

import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
public interface IAlbumService {

    /**
     * 上传
     */
    Album upload(HttpServletRequest request,MultipartFile file, String address);

    /**
     * 删除
     */
    void del(Album album);
}
