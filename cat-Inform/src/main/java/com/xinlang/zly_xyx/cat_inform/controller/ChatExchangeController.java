package com.xinlang.zly_xyx.cat_inform.controller;

import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.fegin.ConsumeProjectUser;
import com.xinlang.zly_xyx.cat_inform.service.IChatExchangeMessageService;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/exchange")
public class ChatExchangeController {
    @Resource
    private IChatExchangeMessageService chatExchangeMessageService;

    @GetMapping
    public List<ChatMessage> getMsg() {
        Map<String, Object> params = new HashMap<>();
        params.put("conversationId", "exchange");
        List<ChatMessage> results = chatExchangeMessageService.findListByParams(params, ChatMessage.class);
		results.sort((ChatMessage o1, ChatMessage o2)->o2.getId().compareTo(o1.getId()));
        return results;
    }
}
