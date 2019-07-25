package com.xinlang.zly_xyx.cat_auth.controller;

import com.xinlang.zly_xyx.cat_log_operate.LogMqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Slf4j
@RestController
@RequestMapping
public class OAuth2Controller {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;
//    @Autowired
//    private LogMqClient logMqClient;

    /**
     * 登录
     * @return
     */
    @GetMapping("/user-me")
    public Authentication principal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("user-me:{}",authentication);
        return authentication;
    }

    /**
     * 退出登录
     * @param access_token
     */
    @DeleteMapping(value = "/remove_token",params = "access_token")
    public void removeToken(String access_token){
        boolean flag = consumerTokenServices.revokeToken(access_token);
        if(flag){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            saveLogOutLog(authentication.getName());
        }
    }

    /**
     * 退出日志
     * @param username
     */
    private void saveLogOutLog(String username){
        log.info("{}退出");
       // logMqClient.sendLogMsg("退出",username,null,null,true);
    }

}
