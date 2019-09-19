package com.xinlang.zly_xyx.cat_inform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CatInformApplication {
    public static void main(String[] args){
        SpringApplication.run(CatInformApplication.class,args);
    }
}