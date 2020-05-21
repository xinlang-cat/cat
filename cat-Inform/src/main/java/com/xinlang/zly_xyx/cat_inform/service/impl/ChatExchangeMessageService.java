package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.json.JsonUtil;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.Mapper.ChatMessageMapper;
import com.xinlang.zly_xyx.cat_inform.Mapper.ChatMessageUserMapper;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;
import com.xinlang.zly_xyx.cat_inform.controller.ChatExchangeServer;
import com.xinlang.zly_xyx.cat_inform.controller.ChatServer;
import com.xinlang.zly_xyx.cat_inform.service.IChatExchangeMessageService;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ChatExchangeMessageService extends BaseService<ChatMessage> implements IChatExchangeMessageService {

    @Resource
    private ChatMessageMapper chatMessageMapper;

    @Override
    public void sendMsgToUser(String message) {
        ChatMessage chatMessage = JsonUtil.strToObject(message, ChatMessage.class);
        chatMessage.setCreateDate(new Date());
        chatMessageMapper.insert(chatMessage);
        ChatExchangeServer.broadcast(JSONArray.fromObject(chatMessage).toString());
    }
}
