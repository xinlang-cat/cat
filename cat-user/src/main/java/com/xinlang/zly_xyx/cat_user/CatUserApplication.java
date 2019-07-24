package com.xinlang.zly_xyx.cat_user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CatUserApplication {
    public static void main(String[] args){
        SpringApplication.run(CatUserApplication.class,args);
    }
}
