package com.xinalng.zly_xyx.cat_gateway.catgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CatGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatGatewayApplication.class, args);
    }

}
