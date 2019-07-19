package com.xinlang.zly_xyx.cat_file_server.service.impl;

import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.bean.FileSource;
import com.xinlang.zly_xyx.cat_file_server.mapper.FileMapper;
import com.xinlang.zly_xyx.cat_file_server.service.IFileService;
import com.xinlang.zly_xyx.cat_file_server.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Slf4j
public abstract class AbstractFileService  implements IFileService {

    protected abstract FileMapper getFileMapper();

    protected abstract FileSource source();

    protected abstract void uploadFile(MultipartFile multipartFile,File file)throws Exception;

    protected abstract boolean delFile(File file);

    /**
     *
     * upload files
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @Override
    public File upload(MultipartFile multipartFile) throws  Exception{
        File file = FileUtil.getFile(multipartFile);
        File oldFile = getFileMapper().getById(file.getId());
        //避免重复上传
        if(oldFile != null){
            return oldFile;
        }
        if(!file.getName().contains(".")){
            throw new IllegalArgumentException("缺少后缀名" );
        }
        uploadFile(multipartFile,file);
        //设置文件来源
        file.setSource(source().name());
        //将文件信息保存到数据库
        getFileMapper().save(file);
        log.info("上传文件:{}",file);
        return file;
    }

    /**
     *
     * delete files
     * @param file
     */
    @Override
    public void del(File file){
        delFile(file);
        getFileMapper().delete(file.getId());
        log.info("删除文件：{}",file);
    }
}
