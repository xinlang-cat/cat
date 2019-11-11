package com.xinlang.zly.summary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProjectSummaryApplication {
    public static void main(String[] args){
        SpringApplication.run(ProjectSummaryApplication.class,args);
    }
}
