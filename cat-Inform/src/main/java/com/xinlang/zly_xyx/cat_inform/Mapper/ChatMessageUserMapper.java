package com.xinlang.zly_xyx.cat_inform.Mapper;

import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ChatMessageUserMapper extends tk.mybatis.mapper.common.Mapper<ChatMessageUser> {
    @Select("select DISTINCT user_id,send_id from chat_message_user t where t.user_id=#{userId} and send_type=2 and (send='N' or is_read='N')")
    List<ChatMessageUser> getOnRead(Integer userId);

    @Update("UPDATE chat_message_user SET send='Y', is_read='Y' WHERE send_id=#{sendId} and send_type=2")
    void updateReadSend(Integer sendId);

    @Update("UPDATE chat_message_user SET send='Y', is_read='Y' WHERE user_id=#{sendId} and send_type=2")
    void updateReadSend1(Integer userId);
}
