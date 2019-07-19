package com.xinlang.zly_xyx.cat_file_server.service;

import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
public interface IFileService {

    /**
     * 上传
     */
    File upload(MultipartFile file) throws  Exception;

    /**
     * 删除
     */
    void del(File file);
}
