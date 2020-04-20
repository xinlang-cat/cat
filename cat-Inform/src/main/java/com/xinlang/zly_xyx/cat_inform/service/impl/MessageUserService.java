package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.bean.MessageUser;
import com.xinlang.zly_xyx.cat_inform.service.IMessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageUserService extends BaseService<MessageUser> implements IMessageUserService {
    @Autowired
    private MessageUserService messageUserMapper;
}
