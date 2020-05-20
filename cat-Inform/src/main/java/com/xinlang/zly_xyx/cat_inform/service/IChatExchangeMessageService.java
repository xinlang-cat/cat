package com.xinlang.zly_xyx.cat_inform.service;

import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;

import java.io.IOException;
import java.util.List;

public interface IChatExchangeMessageService extends IBaseService<ChatMessage> {
    /**
     * 向指定用户发送消息
     *
     * @param message
     * @throws IOException
     */
    void sendMsgToUser(String message) throws IOException;

}
