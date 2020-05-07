package com.xinlang.zly_xyx.cat_user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xinlang.zly_xyx.cat_user.config.WechatConfig;
import com.xinlang.zly_xyx.cat_user.mapper.AppUserMapper;
import com.xinlang.zly_xyx.cat_user.mapper.UserCredentialsMapper;
import com.xinlang.zly_xyx.cat_user.mapper.WechatMapper;
import com.xinlang.zly_xyx.cat_user.service.IWechatService;
import com.xinlang.zly_xyx.user.*;
import com.xinlang.zly_xyx.user.constants.CredentialType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@Slf4j
@Service
public class WechatService implements IWechatService {

    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WechatMapper wechatMapper;
    @Autowired
    private UserCredentialsMapper userCredentialsMapper;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AppUserMapper appUserMapper;

    private static final String WECHAT_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=%s#wechat_redirect";
    private static final String STATE_WECHAT = "state_wechat";
    private static final String WECHAT_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
    private static final String WECHAT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    @Override
    public String getWechatAuthorizeUrl(String app, HttpServletRequest request, String toUrl) throws UnsupportedEncodingException {
        log.info("授权页面:{},{}",app,toUrl);
        WechatInfo wechatInfo = getWechatInfo(app);
        String domain = wechatConfig.getDomain();
        StringBuilder redirectUri = new StringBuilder(domain+"/api-u/wechat/"+app+"/back");
        if(!StringUtils.isBlank(toUrl)){
            toUrl = URLEncoder.encode(toUrl,"UTF-8");
            redirectUri.append("?toUrl=").append(toUrl);
        }
        String redirect_uri = URLEncoder.encode(redirectUri.toString(),"UTF-8");
        String state = UUID.randomUUID().toString();
        request.getSession().setAttribute(STATE_WECHAT,state);
        return String.format(WECHAT_OAUTH_URL,wechatInfo.getAppid(),redirect_uri,state);
    }

    @Transactional
    @Override
    public WechatUserInfo getWechatUserInfo(String app, HttpServletRequest request, String code, String state) {
        checkState(state,request);
        WechatAccess wechatAccess = getWechatAccess(app,code);
        WechatUserInfo wechatUserInfo = wechatMapper.findByOpenid(wechatAccess.getOpenid());
        if(wechatUserInfo == null){
            wechatUserInfo = saveWechatUserInfo(app,wechatAccess);
        }else{
            updateWechatUserInfo(wechatAccess,wechatUserInfo);
        }
        return wechatUserInfo;
    }

    @Override
    public String getToUrl(String toUrl, WechatUserInfo wechatUserInfo){
        StringBuilder stringBuilder = new StringBuilder(toUrl);
        if(!toUrl.contains("?")){
            stringBuilder.append("?");
        }

        if(wechatUserInfo.getUserId() != null){
            stringBuilder.append("&hasUser=1");
        }
        stringBuilder.append("&openid=").append(wechatUserInfo.getOpenid());
        String tempCode = cacheWechatUserInfo(wechatUserInfo);
        stringBuilder.append("&tempCode=").append(tempCode);
        stringBuilder.append("&headimgurl=").append(wechatUserInfo.getHeadimgurl());
        String aa = "";
        try {
            aa = URLEncoder.encode(wechatUserInfo.getNickname(),"UTF-8");
        }catch (Exception e){
            System.out.println("出错了");
        }
        stringBuilder.append("&nickname=").append(aa);

        return stringBuilder.toString();
    }

    @Transactional
    @Override
    public void bindingUser(AppUser appUser, String tempCode, String openid) {
        WechatUserInfo wechatUserInfo = checkAndGetWechatUserInfo(tempCode,openid);
        UserCredential userCredential = new UserCredential(openid,CredentialType.WECHAT_OPENID.name(),appUser.getId());
        userCredentialsMapper.save(userCredential);
        log.info("保存微信登录凭证:{}",userCredential);
//        if(appUser.getHeadImgUrl()==null||"".equals(appUser.getHeadImgUrl())){
//            appUser.setHeadImgUrl(wechatUserInfo.getHeadimgurl());
//            appUser.setUpdateTime(new Date());
//            appUserMapper.update(appUser);
//        }
        wechatUserInfo.setUserId(appUser.getId());
        wechatUserInfo.setUpdateTime(new Date());
        wechatMapper.update(wechatUserInfo);
        log.info("{}, 绑定微信成功，给微信设置用户给id:{}",appUser,wechatUserInfo);
    }

    /**
     * 校验并获取缓存中的微信用户信息
     * @param tempCode
     * @param openid
     * @return
     */
    @Override
    public WechatUserInfo checkAndGetWechatUserInfo(String tempCode, String openid) {
        String key = prefixKey(tempCode);
        String string = stringRedisTemplate.opsForValue().get(key);
        if(string == null){
            throw new IllegalArgumentException("code无效，或已过期");
        }
        WechatUserInfo wechatUserInfo = JSONObject.parseObject(string,WechatUserInfo.class);
        if(!wechatUserInfo.getOpenid().equals(openid)){
            throw new IllegalArgumentException("openid不存在");
        }
        stringRedisTemplate.delete(tempCode);
        return wechatUserInfo;
    }

    /**
     * 获取公众号信息
     * @param app
     * @return
     */
    private WechatInfo getWechatInfo(String app){
        WechatInfo wechatInfo = wechatConfig.getInfos().get(app);
        if(wechatInfo == null){
            throw new IllegalArgumentException("未找到"+app);
        }
        return wechatInfo;
    }

    /**
     * 校验state
     * @param state
     * @param req
     */
    private void checkState(String state,HttpServletRequest req){
        HttpSession session = req.getSession();
        String sessionState = (String)session.getAttribute(STATE_WECHAT);
        if(sessionState == null){
            throw new IllegalArgumentException("session state 不存在");
        }
        if (!sessionState.equals(state)) {
            throw new IllegalArgumentException("非法state");
        }
        session.removeAttribute(STATE_WECHAT);
    }

    /**
     * 获取微信返回的用户信息
     * @param app
     * @param code
     * @return
     */
    private WechatAccess getWechatAccess(String app,String code){
        WechatInfo wechatInfo = getWechatInfo(app);
        String accessTokenUrl = String.format(WECHAT_ACCESS_TOKEN_URL,wechatInfo.getAppid(),wechatInfo.getSecret(),code);
        String string = restTemplate.getForObject(accessTokenUrl,String.class);
        WechatAccess wechatAccess = JSONObject.parseObject(string,WechatAccess.class);
        return wechatAccess;
    }

    /**
     * 保存微信用户个人信息
     * @param app
     * @param wechatAccess
     * @return
     * @throws UnsupportedEncodingException
     */
    private WechatUserInfo saveWechatUserInfo(String app,WechatAccess wechatAccess){
        WechatUserInfo wechatUserInfo = getWechatUserInfo(wechatAccess);
        // 多公众号支持
        String uuid = wechatUserInfo.getUnionid();
        if(StringUtils.isNoneBlank(uuid)){
            Set<WechatUserInfo> set = wechatMapper.findByUniond(uuid);
            if(CollectionUtils.isNotEmpty(set)){
                WechatUserInfo wui = set.parallelStream().filter(w->w.getUserId() != null).findFirst().orElse(null);
                if(wui != null){
                    wechatUserInfo.setUserId(wui.getUserId());
                    log.info("有相同的UUID，是同一用户:{}",wui);
                    userCredentialsMapper.save(new UserCredential(wechatUserInfo.getOpenid(), CredentialType.WECHAT_OPENID.name(),wui.getUserId()));
                }
            }
        }

        wechatUserInfo.setApp(app);
        wechatUserInfo.setCreateTime(new Date());
        wechatUserInfo.setUpdateTime(wechatUserInfo.getCreateTime());
        wechatMapper.save(wechatUserInfo);
        log.info("保存微信用户个人信息:{}",wechatUserInfo);
        return wechatUserInfo;
    }

    /**
     * 获取用户微信个人信息
     * @param wechatAccess
     * @return
     * @throws UnsupportedEncodingException
     */
    private WechatUserInfo getWechatUserInfo (WechatAccess wechatAccess){
        String userInfoUrl = String.format(WECHAT_USERINFO_URL,wechatAccess.getAccess_token(),wechatAccess.getOpenid());
        String string = restTemplate.getForObject(userInfoUrl,String.class);
       try{
           string = new String(string.getBytes("ISO-8859-1"),"UTF-8");
       }catch (UnsupportedEncodingException e){
           e.printStackTrace();
       }
        WechatUserInfo wechatUserInfo = JSONObject.parseObject(string,WechatUserInfo.class);
        log.info("wechatUserInfo:{}",wechatUserInfo);
        return wechatUserInfo;
    }

    /**
     * 异步更新微信用户信息
     * @param wechatAccess
     * @param wechatUserInfo
     */
    private void updateWechatUserInfo(WechatAccess wechatAccess,WechatUserInfo wechatUserInfo){
        taskExecutor.execute(()->{
            WechatUserInfo wui = getWechatUserInfo(wechatAccess);
            BeanUtils.copyProperties(wui,wechatUserInfo,new String[]{"id","userId"});
            wechatUserInfo.setUpdateTime(new Date());
            wechatMapper.update(wechatUserInfo);
            log.info("更新微信用户信息:{}",wechatUserInfo);
        });
    }

    /**
     * 缓存微信信息
     * @param wechatUserInfo
     * @return
     */
    private String cacheWechatUserInfo(WechatUserInfo wechatUserInfo){
        String tempCode = UUID.randomUUID().toString();
        String key = prefixKey(tempCode);
        stringRedisTemplate.opsForValue().set(key,JSONObject.toJSONString(wechatUserInfo),4, TimeUnit.HOURS);
        log.info("将微信信息缓存到redis,{},{}",tempCode,wechatUserInfo);
        return tempCode;
    }

    /**
     * 生成KEY
     * @param tempCode
     * @return
     */
    private String prefixKey(String tempCode){
        return "wechat:temp:" + tempCode;
    }

}
