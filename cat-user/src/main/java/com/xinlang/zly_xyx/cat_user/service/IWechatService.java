package com.xinlang.zly_xyx.cat_user.service;

import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.WechatUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public interface IWechatService {

    String getWechatAuthorizeUrl(String app, HttpServletRequest request, String toUrl) throws UnsupportedEncodingException;

    WechatUserInfo getWechatUserInfo(String app, HttpServletRequest request, String code, String state);

    String getToUrl(String toUrl, WechatUserInfo wechatUserInfo);

    void bindingUser(AppUser appUser, String tempCode, String openid);

    WechatUserInfo checkAndGetWechatUserInfo(String tempCode, String openid);
}
