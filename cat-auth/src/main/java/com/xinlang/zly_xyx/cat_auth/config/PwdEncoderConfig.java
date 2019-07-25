package com.xinlang.zly_xyx.cat_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Configuration
public class PwdEncoderConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
