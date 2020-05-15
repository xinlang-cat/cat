package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChatMessageUserService extends BaseService<ChatMessageUser> implements IChatMessageUserService {
}
