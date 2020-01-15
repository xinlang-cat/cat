package com.xinlang.zly_xyx.cat_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * eureka注册中心
 * @author 张龙毅 18777811286@163.com
 * 2019/7/15
 */
@EnableEurekaServer
@SpringBootApplication
public class  CatEurekaApplication {
    public static void main(String[] args){
        SpringApplication.run(CatEurekaApplication.class,args);
    }
}
