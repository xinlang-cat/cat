package com.xinlang.zly_xyx.cat_inform.service;

import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;

import java.io.IOException;
import java.util.List;

public interface IChatMessageService extends IBaseService<ChatMessage> {
	/**
	 * 向指定用户发送消息
	 *
	 * @param message
	 * @throws IOException
	 */
	void sendMsgToUser(String message) throws IOException;

	/**
	 * 用户登录时检查用户的所有未接收消息
	 *
	 * @throws IOException
	 */
	List<ChatMessage> getMsgByUserId(Integer userId);
	List<ChatMessageUser> getOnRead(Integer userId);
	void updateReadSend(Integer sendId);
	void updateReadSend1(Integer userId);
}
