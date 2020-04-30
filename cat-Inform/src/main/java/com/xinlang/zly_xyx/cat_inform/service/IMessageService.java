package com.xinlang.zly_xyx.cat_inform.service;

import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.message.Message;

import java.util.List;
import java.util.Set;

public interface IMessageService extends IBaseService<Message> {
    List<Message> findByIds(Set<Integer> ids);
}
