package com.xinlang.zly_xyx.cat_inform.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@Data
public class Sms implements Serializable {

    private static final long serialVersionUID = -6371045530553110364L;

    private Long id;
    private String phone;
    private String signName;
    private String templateCode;
    private String params;
    private String bizId;
    private String code;
    private String message;
    private Date day;
    private Date createTime;
    private Date updateTime;
}
