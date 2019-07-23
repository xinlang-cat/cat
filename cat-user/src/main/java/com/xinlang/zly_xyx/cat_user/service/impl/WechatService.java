package com.xinlang.zly_xyx.cat_user.service.impl;

import com.xinlang.zly_xyx.cat_user.mapper.WechatMapper;
import com.xinlang.zly_xyx.cat_user.service.IWechatService;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.WechatInfo;
import com.xinlang.zly_xyx.user.WechatUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@Slf4j
@Service
@Transactional
public class WechatService implements IWechatService {

    @Autowired
    private WechatMapper wechatMapper;

    private static final String WECHAT_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
    private static final String STATE_WECHAT = "state_wechat";


    @Override
    public String getWechatAuthorizeUrl(String app, HttpServletRequest request, String toUrl) throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public WechatUserInfo getWechatUserInfo(String app, HttpServletRequest request, String code, String state) {
        return null;
    }

    @Override
    public String getToUrl(String toUrl, WechatUserInfo wechatUserInfo) {
        return null;
    }

    @Override
    public void bindingUser(AppUser appUser, String tempCode, String openid) {

    }

    @Override
    public WechatUserInfo checkAndGetWechatUserInfo(String tempCode, String openid) {
        return null;
    }

    private WechatInfo getWechatInfo(String app){
        return null;
    }

}
