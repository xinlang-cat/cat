package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.json.JsonUtil;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_inform.Mapper.ChatMessageMapper;
import com.xinlang.zly_xyx.cat_inform.Mapper.ChatMessageUserMapper;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;
import com.xinlang.zly_xyx.cat_inform.controller.ChatServer;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import com.xinlang.zly_xyx.user.AppUser;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChatMessageService extends BaseService<ChatMessage> implements IChatMessageService {

    @Resource
    private ChatMessageMapper chatMessageMapper;
    @Resource
    private ChatMessageUserMapper chatMessageUserMapper;

    @Override
    public void sendMsgToUser(String message) throws IOException {
        ChatMessage chatMessage = JsonUtil.strToObject(message, ChatMessage.class);
        List<Long> userIds = chatMessage.getReceptionUserIds();
        chatMessage.setCreateDate(new Date());
        chatMessageMapper.insert(chatMessage);
        Integer chatMessageId = chatMessage.getId();
        ChatMessageUser cmu = new ChatMessageUser();
        userIds.forEach(item -> {
            cmu.setId(null);
            cmu.setSend(null);
            cmu.setUserId(item);
            cmu.setMessageId(chatMessageId);
            boolean flag = false;
            try {
                flag = ChatServer.sendMsgToUsers(item, JSONArray.fromObject(chatMessage).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (flag) cmu.setSend("Y");
            cmu.setIsRead("N");
            chatMessageUserMapper.insert(cmu);
        });
    }

    @Override
    public List<ChatMessage> getMsgByUser(Long userId) {
        return null;//chatMessageMapper.getMsgByUser(userId, 0, 20);
    }


    @Override
    public List<ChatMessage> getMsgByUsers(Long userId, Long thisUserId) {
        return null;//chatMessageMapper.getMsgByUsers(userId, thisUserId, 0, 8);
    }

}
