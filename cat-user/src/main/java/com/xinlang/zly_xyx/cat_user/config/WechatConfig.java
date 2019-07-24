package com.xinlang.zly_xyx.cat_user.config;

import com.xinlang.zly_xyx.user.WechatInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties( prefix = "wechat")
@Component
public class WechatConfig {
    private String domain;
    private Map<String, WechatInfo> infos;
}
