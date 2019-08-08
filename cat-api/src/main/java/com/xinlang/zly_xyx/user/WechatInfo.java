package com.xinlang.zly_xyx.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 微信appid和secret对象
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Getter
@Setter
@ToString
public class WechatInfo implements Serializable {


    private static final long serialVersionUID = -5833632736189955094L;
    private String appid;
    private String secret;
}
