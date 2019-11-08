package com.xinlang.zly_xyx.cat_file_server.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Data
public class Album {

    private String id;
    private String name;
    private Integer userId;
    private String contentType;
    private Boolean isWeChat;
    private long size;
    private String path;
    private String url;
    /**
     * 照片来源
     * @see Source
     */
    private String source;
    private Date createTime;
}
