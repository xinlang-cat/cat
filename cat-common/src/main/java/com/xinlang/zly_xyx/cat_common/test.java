package com.xinlang.zly_xyx.cat_common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/18
 */
@SpringBootApplication
@EnableEurekaClient
public class test {

    public static void main(String[] args){
        SpringApplication.run(test.class,args);
    }
}
