package com.xinlang.zly.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CatMapServerApplication {
    public static void main(String[] args){
        SpringApplication.run(CatMapServerApplication.class,args);
    }
}
