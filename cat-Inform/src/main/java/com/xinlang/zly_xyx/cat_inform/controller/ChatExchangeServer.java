package com.xinlang.zly_xyx.cat_inform.controller;

import com.alibaba.fastjson.JSONObject;
import com.xinlang.zly_xyx.cat_inform.config.HttpExchangeSessionConfigurator;
import com.xinlang.zly_xyx.cat_inform.config.HttpSessionConfigurator;
import com.xinlang.zly_xyx.cat_inform.service.IChatExchangeMessageService;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务
 *
 * @time : 2016.01.08 09:50
 */
@Component
@ServerEndpoint(value = "/websocket-exchange", configurator = HttpExchangeSessionConfigurator.class)
public class ChatExchangeServer {

    private static IChatExchangeMessageService chatExchangeMessageService;
    private static CopyOnWriteArraySet<ChatExchangeServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session; // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    public static void setApplicationContext(ApplicationContext applicationContext){
        chatExchangeMessageService = applicationContext.getBean(IChatExchangeMessageService.class);
    }


    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        webSocketSet.add(this); // 加入set中
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); // 从set中删除
    }

    /**
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        try {
            chatExchangeMessageService.sendMsgToUser(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     *
     * @param error 错误信息
     */
    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    /**
     * 广播消息
     *
     * @param message 客户端发送过来的消息
     */
    public static void broadcast(String message) {
        for (ChatExchangeServer chat : webSocketSet) {
            try {
                chat.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
