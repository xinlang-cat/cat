package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.bean.FileSource;
import com.xinlang.zly_xyx.cat_file_server.mapper.FileMapper;
import com.xinlang.zly_xyx.cat_file_server.utils.FileUtil;
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
@Service("localFileService")
public class LocalFileService extends AbstractFileService{

    @Autowired
    private FileMapper fileMapper;

    @Value("${file.local.urlPrefix}")
    private String urlPrefix;
    /**
     * The upload file is stored in the local root path
     */
    @Value("${file.local.path}")
    private String localFilePath;

    @Override
    protected FileMapper getFileMapper() {
        return fileMapper;
    }

    @Override
    protected FileSource source() {
        return FileSource.LOCAL;
    }

    @Override
    protected void uploadFile(MultipartFile multipartFile, File file) throws Exception {
        int index = file.getName().lastIndexOf(".");
        String fileSuffix = file.getName().substring(index);
        String suffix = "/"+ LocalDate.now().toString().replace("-","/")+"/"+file.getId()+fileSuffix;
        String path = localFilePath+suffix;
        String url = urlPrefix+suffix;
        file.setPath(path);
        file.setUrl(url);
        FileUtil.saveFile(multipartFile,path);
    }

    @Override
    protected boolean delFile(File file) {
        return false;
    }
}
