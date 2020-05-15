package com.xinlang.zly_xyx.cat_inform.Mapper;

import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface ChatMessageMapper extends Mapper<ChatMessage> {
	public List<ChatMessage>  getMsgByUser(@Param("userid") Integer userid, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
	public List<ChatMessage>  getMsgByUsers(@Param("userid") Integer userid, @Param("thisUserid") Integer thisUserid, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

}
