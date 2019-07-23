package com.xinlang.zly_xyx.cat_log.config;

import com.xinlang.zly_xyx.log.constants.LogQueue;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * rabbitmq配置
 * 

 *
 */
@Configuration
public class RabbitmqConfig {

	/**
	 * 声明队列
	 * 
	 * @return
	 */
	@Bean
	public Queue logQueue() {
		Queue queue = new Queue(LogQueue.LOG_QUEUE);

		return queue;
	}
}
