package com.xinlang.zly_xyx.cat_auth.service.impl;

import com.xinlang.zly_xyx.cat_auth.fegin.ConsumeInform;
import com.xinlang.zly_xyx.cat_auth.fegin.ConsumeUser;
import com.xinlang.zly_xyx.user.LoginAppUser;
import com.xinlang.zly_xyx.user.constants.CredentialType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ConsumeUser consumeUser;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ConsumeInform consumeInform;


    /**
     * 根据用户名获取用户
     * @param param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        String[] params = param.split("\\|");
        String username = params[0];
        LoginAppUser loginAppUser = consumeUser.findByUsername(username);
        if(loginAppUser == null){
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }else if(!loginAppUser.isEnabled()){
            throw new DisabledException("用户已禁用");
        }
        if(params.length>1){
            CredentialType credentialType = CredentialType.valueOf(params[1]);
            if(CredentialType.PHONE == credentialType){
                handlerPhoneLogin(loginAppUser,params);
            }else if(CredentialType.WECHAT_OPENID == credentialType){
                handlerWechatLogin(loginAppUser,params);
            }
        }
        return loginAppUser;
    }

    /**
     * 手机号码登录的用户
     * @param loginAppUser
     * @param params
     */
    private void handlerPhoneLogin(LoginAppUser loginAppUser,String[] params){
        if(params.length>5 || params.length<1){
            throw new IllegalArgumentException("非法请求");
        }
        String phone = params[0];
        String key = params[2];
        String code = params[3];
        String md5 = params[4];
        if(!DigestUtils.md5Hex(key+code).equals(md5)){
            throw new IllegalArgumentException("非法请求");
        }
        String value = consumeInform.matcheCodeAndGetPhone(key,code,false,30);
        if(StringUtils.equals(phone,value)){
            throw new IllegalArgumentException("验证码错误");
        }
        loginAppUser.setPassword(passwordEncoder.encode(phone));
        log.info("手机号码登录：{},{}",phone,code);
    }

    /**
     * 微信登录
     * @param loginAppUser 登录的用户
     * @param params openid,tempCode
     */
    private void handlerWechatLogin(LoginAppUser loginAppUser,String[] params){
        if(params.length>3 || params.length<1){
            throw new IllegalArgumentException("非法请求");
        }
        String openid = params[0];
        String tempCode = params[2];
        consumeUser.wechatLoginCheck(tempCode,openid);
        loginAppUser.setPassword(passwordEncoder.encode(tempCode));
        log.info("微信登录:{}，{}",loginAppUser,openid);
    }

}
