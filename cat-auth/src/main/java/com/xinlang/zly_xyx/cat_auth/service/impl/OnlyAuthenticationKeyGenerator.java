package com.xinlang.zly_xyx.cat_auth.service.impl;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

/**
 * 避免用户的key相同
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
public class OnlyAuthenticationKeyGenerator implements AuthenticationKeyGenerator {

        @Override
        public String extractKey(OAuth2Authentication auth2Authentication){
            return UUID.randomUUID().toString();
        }
}
