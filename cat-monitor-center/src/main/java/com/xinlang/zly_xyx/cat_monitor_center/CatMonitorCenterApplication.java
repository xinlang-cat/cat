package com.xinlang.zly_xyx.cat_monitor_center;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 监控中心
 * @author 张龙毅 18777811286@163.com
 * 2019/7/29
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
public class CatMonitorCenterApplication {
    public static void main(String[] args){
        SpringApplication.run(CatMonitorCenterApplication.class,args);
    }
}
