package com.xinlang.zly_xyx.cat_inform.controller;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.PostConstruct;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;
import com.xinlang.zly_xyx.cat_inform.config.HttpSessionConfigurator;
import com.xinlang.zly_xyx.cat_inform.fegin.ConsumeProjectUser;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * websocket服务
 *
 * @time : 2016.01.08 09:50
 */
@Component
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
public class ChatServer {

    private static IChatMessageService chatMessageService;
    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session; // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private static Map<Integer, Session> routetab = new HashMap<>(); // 用户id和websocket的session绑定的路由表
    private static Set<Integer> users = new HashSet<>();//
    private Integer userId;
    private static List<CustomerServiceStaff> customerServiceStaffs;
    public static void setApplicationContext(ApplicationContext applicationContext){
        chatMessageService = applicationContext.getBean(IChatMessageService.class);
    }


    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        webSocketSet.add(this); // 加入set中
        Integer userId = Integer.valueOf(config.getUserProperties().get("userId").toString());
        this.userId = userId;
        users.add(userId);
        routetab.put(userId, session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); // 从set中删除
        users.remove(userId);
        routetab.remove(userId);
    }

    /**
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message) {
        try {
            chatMessageService.sendMsgToUser(message);
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
    public void broadcast(String message) {
        for (ChatServer chat : webSocketSet) {
            try {
                chat.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对特定用户发送消息
     *
     * @param message 客户端发送过来的消息
     * @param session 会话
     */
    public void singleSend(String message, Session session) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 组装返回给前台的消息
     *
     * @param message 交互信息
     * @param type    信息类型
     * @param list    在线列表
     * @return
     */
    public String getMessage(String message, String type, Map<Integer, AppUser> list) {
        JSONObject member = new JSONObject();
        member.put("message", message);
        member.put("type", type);
        member.put("list", list);
        return member.toString();
    }

    /**
     * 发到指定用户
     *
     * @throws IOException
     */
    public static boolean sendMsgToUsers(Integer userId, String context) throws IOException {
        Session session = routetab.get(userId);
        boolean flag = false;
        if (session != null) {
            session.getBasicRemote().sendText(context);
            flag = true;
        }
        return flag;
    }

    private void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static Set<Integer> getList() {
        return users;
    }
}
