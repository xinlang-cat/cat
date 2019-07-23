package com.xinlang.zly_xyx.cat_user.config;


import com.xinlang.zly_xyx.user.constants.UserCenterMq;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(UserCenterMq.MQ_EXCHANGE_USER);
    }
}
