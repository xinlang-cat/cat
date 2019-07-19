package com.xinlang.zly_xyx.cat_file_server.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
public class AliyunConfig {

    @Value("${file.aliyun.endpoint")
    private String endpoint;
    @Value("${file.aliyun.accessKeyId")
    private String accessKeyId;
    @Value("${file.aliyun.accessKeySecret")
    private String accessKeySecret;


    /**
     * aliyun file config.
     * @return
     */
    @Bean
    public OSSClient ossClient(){
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        return ossClient;
    }
}
