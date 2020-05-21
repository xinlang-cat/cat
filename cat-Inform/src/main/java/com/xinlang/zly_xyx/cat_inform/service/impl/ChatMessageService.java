package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.json.JsonUtil;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.Mapper.ChatMessageMapper;
import com.xinlang.zly_xyx.cat_inform.Mapper.ChatMessageUserMapper;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;
import com.xinlang.zly_xyx.cat_inform.controller.ChatServer;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ChatMessageService extends BaseService<ChatMessage> implements IChatMessageService {

    @Resource
    private ChatMessageMapper chatMessageMapper;
    @Resource
    private ChatMessageUserMapper chatMessageUserMapper;

    @Override
    public void sendMsgToUser(String message) {
        ChatMessage chatMessage = JsonUtil.strToObject(message, ChatMessage.class);
        List<Integer> userIds = chatMessage.getReceptionUserIds();
        HashSet<Integer> h = new HashSet<>(userIds);
        userIds.clear();
        userIds.addAll(h);
        chatMessage.setCreateDate(new Date());
        chatMessageMapper.insert(chatMessage);
        Integer chatMessageId = chatMessage.getId();
        ChatMessageUser cmu = new ChatMessageUser();
        cmu.setSendType(chatMessage.getSendType());
        cmu.setSendId(chatMessage.getCreateUserId());
        cmu.setMessageId(chatMessageId);
        for (int i = 0; i < userIds.size(); i++) {
            cmu.setId(null);
            cmu.setUserId(userIds.get(i));
            boolean flag = false;
            try {
                flag = ChatServer.sendMsgToUsers(userIds.get(i), JSONArray.fromObject(chatMessage).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (flag) {
                cmu.setSend("Y");
            }else {
                cmu.setSend("N");
            }
            if (chatMessage.getCreateUserId() == userIds.get(i)) {
                cmu.setIsRead("Y");
            } else {
                cmu.setIsRead("N");
            }
            chatMessageUserMapper.insert(cmu);
        }
    }

    @Override
    public List<ChatMessage> getMsgByUserId(Integer userId) {
        Example example = new Example(ChatMessageUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("send_type=2 and (send_id=" + userId + " or user_id=" + userId + ")");
        List<ChatMessageUser> chatMessageUsers = chatMessageUserMapper.selectByExample(example);
        if (chatMessageUsers.isEmpty()) {
            return null;
        }
        Set<Integer> messageIds = new HashSet<>();
        chatMessageUsers.forEach(item -> {
            messageIds.add(item.getMessageId());
        });
        Example example1 = new Example(ChatMessage.class);
        example1.createCriteria().andIn("id", messageIds);
        example1.setOrderByClause("id DESC");
        return chatMessageMapper.selectByExample(example1);
    }

    @Override
    public List<ChatMessageUser> getOnRead(Integer userId) {
        return chatMessageUserMapper.getOnRead(userId);
    }

    @Override
    public void updateReadSend(Integer sendId) {
        chatMessageUserMapper.updateReadSend(sendId);
    }

    @Override
    public void updateReadSend1(Integer userId) {
        chatMessageUserMapper.updateReadSend1(userId);
    }

}
