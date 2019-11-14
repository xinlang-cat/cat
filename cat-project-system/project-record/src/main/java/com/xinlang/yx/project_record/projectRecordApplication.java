package com.xinlang.yx.project_record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 杨珣
 * @date 2019-11-12
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class projectRecordApplication {
    public static void main(String[] args){
        SpringApplication.run(projectRecordApplication.class,args);
    }
}
