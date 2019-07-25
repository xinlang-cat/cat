package com.xinlang.zly_xyx.cat_log_operate;


import com.xinlang.zly_xyx.log.constants.LogQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAutoConfiguration {
    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue logqueue(){
        return new Queue(LogQueue.LOG_QUEUE);
    }
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Bean
    public LogMqClient logMqClient(){
        return new LogMqClient(amqpTemplate);
    }
}
