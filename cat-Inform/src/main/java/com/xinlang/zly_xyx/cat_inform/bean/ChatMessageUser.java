package com.xinlang.zly_xyx.cat_inform.bean;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="chat_message_user")
public class ChatMessageUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Long userId;//接受者id
	private Integer messageId;//消息id
	private Long senderId;//发送者id
	private String send;//是否已发送给接收者
	private String isRead;//是否已读
}
