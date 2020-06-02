package com.xinlang.zly_xyx.cat_file_server.controller;

import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.config.AlbumServiceFactory;
import com.xinlang.zly_xyx.cat_file_server.mapper.AlbumMapper;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.xinlang.zly_xyx.cat_file_server.service.IAlbumService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumServiceFactory albumServiceFactory;
    @Autowired
    private AlbumMapper albumMapper;
    @Value("${album.local.urlPrefix}")
    private String urlPrefix;



    @LogAnnotation(module = "上传照片到相册", recordParam = false)
    @ApiOperation(value = "上传照片到相册")
    @PostMapping
    public Album upload(HttpServletRequest request ,@RequestParam("file") MultipartFile multipartFile, String source,String address ){
        IAlbumService fileService = albumServiceFactory.getAlbumService(source);
        return fileService.upload(request,multipartFile,address);
    }


    @LogAnnotation(module = "上传照片到相册", recordParam = false)
    @ApiOperation(value = "上传照片到相册")
    @PostMapping("/layui")
    public Map<String,Object> uploadLayui(HttpServletRequest request,@RequestParam("file")MultipartFile multipartFile, String source,String address) throws Exception{
        Album album = upload(request,multipartFile,source,address);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        Map<String,Object> data = new HashMap<>();
        data.put("src",album.getUrl());
        map.put("data",data);
        return map;
    }


    @LogAnnotation(module = "删除照片")
    @ApiOperation(value = "删除照片")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        Album album = albumMapper.getById(id);
        if(album != null){
            String source = album.getSource();
            IAlbumService albumService = albumServiceFactory.getAlbumService(source);
            albumService.del(album);
        }
    }


    @GetMapping
    @LogAnnotation(module = "查询相册")
    @ApiOperation(value = "查询相册")
    public Page<Album> findFiles(@RequestParam Map<String, Object> params) {
        int total = albumMapper.count(params);
        List<Album> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list = albumMapper.findData(params);
            list.forEach(item->{
                item.setUrl(setFileUrl(item));
            });
        }
        return new Page<>(total, list);
    }

    @GetMapping("/{id}")
    public Album findById(@PathVariable String id) {
        Album album = albumMapper.getById(id);
        album.setUrl(setFileUrl(album));
        return album;
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据ids查询相册")
    public List<Album> findByIds(@RequestParam("ids") Set<String> ids) {
        List<Album> list = albumMapper.getByIds(new ArrayList<>(ids));
        if(list.size()>0){
            list.forEach(item->{
                item.setUrl(setFileUrl(item));
            });
        }
        return list;
    }

    private String setFileUrl(Album item){
        String url = item.getUrl();
        String[] arr = url.split("/statics/");
        return urlPrefix + "/" + arr[1];
    }
}
