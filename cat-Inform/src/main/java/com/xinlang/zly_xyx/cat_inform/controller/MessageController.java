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
        messageService.save(message);
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
        return messageService.findByIds(set);
    }

    @GetMapping("/setIsRead")
    @LogAnnotation(module = "设置已读")
    @ApiOperation(value = "设置已读")
    public void setIsRead(@RequestParam Integer id) {
       MessageUser messageUser = new MessageUser();
       messageUser.setIsRead(1);
       messageUser.setReadTime(new Date());
       messageUser.setId(id);
       messageUserService.update(messageUser);
    }
}
