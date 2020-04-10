package com.xinlang.zly.project_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-04
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ProjectUserApplication {

    public static void main(String[] args){
        SpringApplication.run(ProjectUserApplication.class,args);
    }
}
