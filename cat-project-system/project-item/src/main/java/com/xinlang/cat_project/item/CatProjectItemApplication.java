package com.xinlang.cat_project.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.xinlang.cat_project.item.mapper")
public class CatProjectItemApplication {
    public static void main(String[] args){
        SpringApplication.run(CatProjectItemApplication.class,args);
    }
}
