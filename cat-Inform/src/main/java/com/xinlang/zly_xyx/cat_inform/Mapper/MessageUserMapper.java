package com.xinlang.zly_xyx.cat_inform.Mapper;

import com.xinlang.zly_xyx.cat_inform.bean.MessageUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("messageUserMapper")
public interface MessageUserMapper extends tk.mybatis.mapper.common.Mapper<MessageUser> {
}
