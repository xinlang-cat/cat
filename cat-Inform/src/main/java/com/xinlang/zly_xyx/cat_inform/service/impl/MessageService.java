package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.Mapper.MessageMapper;
import com.xinlang.zly_xyx.message.Message;
import com.xinlang.zly_xyx.cat_inform.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MessageService extends BaseService<Message> implements IMessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> findByIds(Set<Integer> ids) {
        Example example = new Example(Message.class);
        example.createCriteria().andIn("id",ids);
        return messageMapper.selectByExample(example);
    }
}
