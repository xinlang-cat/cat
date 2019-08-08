package com.xinlang.zly_xyx.cat_file_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CatFileServerApplication {

    public static void main(String[] args){
        SpringApplication.run(CatFileServerApplication.class,args);
    }
}
