package com.xinlang.zly_xyx.cat_manage_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 后台管理
 */

@SpringBootApplication
@EnableDiscoveryClient
public class CatManageBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatManageBackendApplication.class,args);

    }
}
