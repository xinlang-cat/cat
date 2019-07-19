package com.xinlang.zly_xyx.cat_file_server.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Data
public class File  {

    private String id;
    private String name;
    private Boolean isImg;
    private String contentType;
    private long size;
    private String path;
    private String url;
    /**
     * 文件来源
     * @see FileSource
     */
    private String source;
    private Date createTime;
}
