package com.xinlang.zly_xyx.cat_file_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * Causes the system to load files outside of the jar
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Configuration
public class LocalFilePathConfig {

    /**
     * The upload file is stored in the local root path
     */
    @Value("${file.local.path}")
    private String localFilePath;
    /**
     * url prefix
     */
    @Value("${file.local.prefix}")
    private String localFilePrefix;

    @Bean
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){
            /**
             * Access to external files
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler(localFilePrefix+"/**")
                        .addResourceLocations(ResourceUtils.FILE_URL_PREFIX+localFilePath+ File.separator);
            }
        };
    }
}
