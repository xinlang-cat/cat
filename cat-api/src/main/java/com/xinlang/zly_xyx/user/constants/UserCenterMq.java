package com.xinlang.zly_xyx.user.constants;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public interface UserCenterMq {

	/**
	 * 用户系统exchange名
	 */
	String MQ_EXCHANGE_USER = "user.topic.exchange";

	/**
	 * 角色删除routing key
	 */
	String ROUTING_KEY_ROLE_DELETE = "role.delete";
}
