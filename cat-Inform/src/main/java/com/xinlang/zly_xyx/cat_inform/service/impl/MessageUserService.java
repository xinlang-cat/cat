package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.Mapper.MessageUserMapper;
import com.xinlang.zly_xyx.cat_inform.bean.MessageUser;
import com.xinlang.zly_xyx.cat_inform.service.IMessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class MessageUserService extends BaseService<MessageUser> implements IMessageUserService {
    @Autowired
    private MessageUserMapper messageUserMapper;

    @Override
    public void update(MessageUser messageUser) {
        Example example = new Example(MessageUser.class);
        example.createCriteria().andEqualTo("userId",messageUser.getUserId()).andEqualTo("messageId",messageUser.getMessageId());
        messageUserMapper.updateByExampleSelective(messageUser,example);
    }
}
