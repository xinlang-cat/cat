package com.xinlang.zly_xyx.cat_file_server.config;

import com.xinlang.zly_xyx.cat_file_server.bean.FileSource;
import com.xinlang.zly_xyx.cat_file_server.service.IFileService;
import com.xinlang.zly_xyx.cat_file_server.service.impl.AliyunFileService;
import com.xinlang.zly_xyx.cat_file_server.service.impl.LocalFileService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * FileService factory
 * put the implementation classes into a map
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Configuration
public class FileServiceFactory {

    @Autowired
    private AliyunFileService aliyunFileService;
    @Autowired
    private LocalFileService localFileService;

    private Map<FileSource, IFileService> map = new HashMap<>();

    @PostConstruct
    public void init(){
        map.put(FileSource.ALIYUN,aliyunFileService);
        map.put(FileSource.LOCAL,localFileService);
    }

    /**
     * Get the concrete implementation class from the file source
     * @param source
     * @return
     */
    public IFileService getFileService(String source){
        if(StringUtils.isBlank(source)){
            return localFileService;
        }
        IFileService fileService = map.get(FileSource.valueOf(source));
        if(fileService == null){
            throw new IllegalArgumentException("FileServiceFactory.init方法初始化失败");
        }
        return fileService;
    }

}
