package com.xinlang.zly_xyx.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-23
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class CatProjectSystemCompanyApplication {

    public static void main(String[] args){
        SpringApplication.run(CatProjectSystemCompanyApplication.class,args);
    }
}
