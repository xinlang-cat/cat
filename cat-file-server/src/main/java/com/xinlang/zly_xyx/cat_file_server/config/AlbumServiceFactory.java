package com.xinlang.zly_xyx.cat_file_server.config;

import com.xinlang.zly_xyx.cat_file_server.bean.Source;
import com.xinlang.zly_xyx.cat_file_server.service.IAlbumService;
import com.xinlang.zly_xyx.cat_file_server.service.IFileService;
import com.xinlang.zly_xyx.cat_file_server.service.impl.AliyunAlbumService;
import com.xinlang.zly_xyx.cat_file_server.service.impl.AliyunFileService;
import com.xinlang.zly_xyx.cat_file_server.service.impl.LocalAlbumService;
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
public class AlbumServiceFactory {

    @Autowired
    private AliyunAlbumService aliyunAlbumService;
    @Autowired
    private LocalAlbumService localAlbumService;

    private Map<Source, IAlbumService> map = new HashMap<>();

    @PostConstruct
    public void init(){
        map.put(Source.ALIYUN,aliyunAlbumService);
        map.put(Source.LOCAL,localAlbumService);
    }

    public IAlbumService getAlbumService(String source){
        if(StringUtils.isBlank(source)){
            return localAlbumService;
        }
        IAlbumService albumService = map.get(Source.valueOf(source));
        if(albumService == null){
            throw new IllegalArgumentException("AlbumServiceFactory.init方法初始化失败");
        }
        return albumService;
    }

}
