package com.xinlang.zly_xyx.cat_file_server.controller;

import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import com.xinlang.zly_xyx.cat_file_server.config.FileServiceFactory;
import com.xinlang.zly_xyx.cat_file_server.mapper.FileMapper;
import com.xinlang.zly_xyx.cat_file_server.service.IFileService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileServiceFactory fileServiceFactory;
    @Autowired
    private FileMapper fileMapper;
    @Value("${file.local.urlPrefix}")
    private String urlPrefix;


    /**
     * file upload <br>
     * choose upload mode according to fileSource, only local upload is implemented so far
     * upload to a third party if necessary, such as aliyun, qiniu, etc
     *
     * @ param file
     * @ param fileSource
     * Source
     *
     * @ return
     * @ throws the Exception
     */
    @LogAnnotation(module = "文件上传", recordParam = false)
    @PostMapping
    public File upload(@RequestParam("file") MultipartFile multipartFile, String source) throws Exception{
       try{
           IFileService fileService = fileServiceFactory.getFileService(source);
           return fileService.upload(multipartFile);
       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }

    /**
     * layui rich text file custom upload
     *
     * @ param file
     * @ param fileSource
     * @ return
     * @ throws the Exception
     */
    @LogAnnotation(module = "文件上传", recordParam = false)
    @PostMapping("/layui")
    public Map<String,Object> uploadLayui(@RequestParam("file")MultipartFile multipartFile,String source) throws Exception{
        File file = upload(multipartFile,source);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        Map<String,Object> data = new HashMap<>();
        data.put("src",file.getUrl());
        map.put("data",data);
        return map;
    }

    /**
     * delete file
     * @param id
     */
    @LogAnnotation(module = "文件删除")
    @PreAuthorize("hasAnyAuthority('file:del')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        File file = fileMapper.getById(id);
        if(file != null){
            String source = file.getSource();
            IFileService fileService = fileServiceFactory.getFileService(source);
            fileService.del(file);
        }
    }



    /**
     * query file
     *
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('file:query')")
    @GetMapping
    public Page<File> findFiles(@RequestParam Map<String, Object> params) {

        int total = fileMapper.count(params);
        List<File> list = Collections.emptyList();
        if (total > 0) {
            PageUtil.pageParamConver(params, true);
            list = fileMapper.findData(params);
            if(list.size()>0){
                list.forEach(item->{
                    item.setUrl(setFileUrl(item));
                });
            }
        }
        return new Page<>(total, list);
    }

    /**
     * query file
     *
     * @param
     * @return
     */
    @PreAuthorize("hasAuthority('file:query')")
    @GetMapping("/{id}")
    public File findById(@PathVariable String id) {
        File file = fileMapper.getById(id);
        file.setUrl(setFileUrl(file));
        return file;
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据ids查询文件")
    public List<File> findByIds(@RequestParam("ids") Set<String> ids) {
        List<File> list = fileMapper.getByIds(new ArrayList<>(ids));
        if(list.size()>0){
            list.forEach(item->{
                item.setUrl(setFileUrl(item));
            });
        }
        return list;
    }

    private String setFileUrl(File item){
        String url = item.getUrl();
        String[] arr = url.split("/statics/");
        return urlPrefix + "/" + arr[1];
    }
}