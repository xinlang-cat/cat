package com.xinlang.zly_xyx.cat_inform.controller;

import javax.annotation.Resource;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
	@Resource
	private IChatMessageService chatMessageService;

	@GetMapping
	public List<ChatMessage> getMsg() {
		return  chatMessageService.getMsgByUser(AppUserUtil.getLoginAppUser().getId());
	}
}
