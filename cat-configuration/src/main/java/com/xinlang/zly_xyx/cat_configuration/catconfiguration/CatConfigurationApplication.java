package com.xinlang.zly_xyx.cat_configuration.catconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 配置服务中心
 * @author 张龙毅 18777811286@163.com
 * 2019/7/15
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class CatConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatConfigurationApplication.class, args);
    }

}
