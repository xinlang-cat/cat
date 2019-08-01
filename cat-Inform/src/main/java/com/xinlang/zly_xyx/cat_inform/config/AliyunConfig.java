package com.xinlang.zly_xyx.cat_inform.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@Configuration
public class AliyunConfig {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public IAcsClient iAcsClient() throws ClientException {
        System.setProperty("sun.net.client.defaultConnectRimeOut","1000");
        System.setProperty("sun.net.client.defaultReadRimeOut","1000");
        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";
        IClientProfile iClientProfile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou",product,domain);
        return new DefaultAcsClient(iClientProfile);
    }
}
