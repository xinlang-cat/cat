package com.xinlang.zly_xyx.cat_inform.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name="chat_message")
public class ChatMessage implements Serializable {
	private static final long serialVersionUID = 1754543494930424467L;
	@Id
	@KeySql(useGeneratedKeys = true)
	private Integer id;
	private Integer type;//0普通消息，1链接
	private Integer createUserId;//发送者id
	private String createUserName;//发送者姓名
	private String context;//消息内容
	private String conversationId;//会话id
	private String messageUrl;//消息链接
	private Date createDate;//发送时间
	private Integer sendType;//1群发，0私聊，2客服的
	private String headImgUrl;//发送人头像
	@Transient
	private List<Integer> receptionUserIds;//接收人Ids;
}
