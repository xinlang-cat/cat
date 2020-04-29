package com.xinlang.zly_xyx.cat_inform.controller;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_inform.bean.Message;
import com.xinlang.zly_xyx.cat_inform.bean.MessageUser;
import com.xinlang.zly_xyx.cat_inform.service.IMessageService;
import com.xinlang.zly_xyx.cat_inform.service.IMessageUserService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private IMessageUserService messageUserService;

    @PostMapping
    @LogAnnotation(module = "添加通知")
    @ApiOperation(value = "添加通知")
    public Message save(@RequestBody Message message) {
        Date date = new Date();
        message.setCreateTime(date);
        messageService.save(message);
        MessageUser messageUser = new MessageUser();
        message.getUserIds().forEach(item -> {
            messageUser.setIsRead(0);
            messageUser.setMessageId(message.getId());
            messageUser.setUserId(item);
            messageUserService.save(messageUser);
            messageUser.setId(null);
        });
        return message;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询通知列表")
    @ApiOperation(value = "查询通知列表")
    public List<Message> findListByParams(@RequestParam Map<String, Object> params) {
        return messageService.findListByParams(params, Message.class);
    }

    @GetMapping("/my")
    @LogAnnotation(module = "查询通知列表")
    @ApiOperation(value = "查询通知列表,参数为空0查询未读，参数为1查询已读，没有参数查询全部")
    public List<Message> findMymMessage(@RequestParam Integer isRead) {
        Map<String, Object> params = new HashMap<>();
        if (isRead != null) {
            params.put("isRead", isRead);
        }
        AppUser appUser = AppUserUtil.getLoginAppUser();
        params.put("userId", appUser.getId().intValue());
        List<MessageUser> messageUsers = messageUserService.findListByParams(params, MessageUser.class);
        Set<Integer> set = new HashSet<>();
        messageUsers.forEach(item -> {
            set.add(item.getMessageId());
        });
        List<Message> data = new ArrayList<>();
        if (set.size() > 0) {
            data = messageService.findByIds(set);
        }
        data.forEach(item -> {
            messageUsers.forEach(messageUser -> {
                if (item.getId() == messageUser.getMessageId()) {
                    item.setIsRead(messageUser.getIsRead());
                }
            });
        });
        return data;
    }

    @GetMapping("/layui/my")
    @LogAnnotation(module = "查询通知列表")
    @ApiOperation(value = "查询通知列表,参数为空0查询未读，参数为1查询已读，没有参数查询全部")
    public Map<String, Object> findMymMessage(@RequestParam Map<String, Object> params) {
        AppUser appUser = AppUserUtil.getLoginAppUser();
        params.put("userId", appUser.getId().intValue());
        Integer count = messageUserService.findListByParams(params, MessageUser.class).size();
        List<MessageUser> messageUsers = messageUserService.findPageByParams(params, MessageUser.class).getData();
        Set<Integer> set = new HashSet<>();
        messageUsers.forEach(item -> {
            set.add(item.getMessageId());
        });
        List<Message> data = new ArrayList<>();
        if (set.size() > 0) {
            data = messageService.findByIds(set);
        }
        data.forEach(item -> {
            messageUsers.forEach(messageUser -> {
                if (item.getId() == messageUser.getMessageId()) {
                    item.setIsRead(messageUser.getIsRead());
                }
            });
        });
        params.put("data", data);
        params.put("code", 0);
        params.put("count", count);
        return params;
    }

    @PutMapping("/setIsRead")
    @LogAnnotation(module = "设置已读")
    @ApiOperation(value = "设置已读")
    public void setIsRead(@RequestBody Set<Integer> ids) {
        MessageUser messageUser = new MessageUser();
        ids.forEach(item -> {
            messageUser.setIsRead(1);
            messageUser.setReadTime(new Date());
            messageUser.setId(item);
            messageUserService.update(messageUser);
        });
    }
}
