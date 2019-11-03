package com.xinlang.zly.label;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-10-31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProjectLabelApplication {

    public static void main(String[] args){
        SpringApplication.run(ProjectLabelApplication.class,args);
    }
}
