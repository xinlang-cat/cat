package com.xinlang.zly_xyx.cat_inform.Mapper;

import com.xinlang.zly_xyx.cat_inform.bean.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("messageMapper")
public interface MessageMapper extends tk.mybatis.mapper.common.Mapper<Message> {
}
