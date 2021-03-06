package com.xinlang.zly_xyx.cat_gateway_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
public class CatGatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatGatewayZuulApplication.class,args);

    }
}
