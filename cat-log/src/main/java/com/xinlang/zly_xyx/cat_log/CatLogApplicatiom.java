package com.xinlang.zly_xyx.cat_log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CatLogApplicatiom {
    public static void main(String[] args) {
        SpringApplication.run(CatLogApplicatiom.class,args);

    }
}
