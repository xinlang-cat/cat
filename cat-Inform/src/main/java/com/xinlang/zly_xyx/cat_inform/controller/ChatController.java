package com.xinlang.zly_xyx.cat_inform.controller;

import javax.annotation.Resource;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessageUser;
import com.xinlang.zly_xyx.cat_inform.fegin.ConsumeProjectUser;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
	@Resource
	private IChatMessageService chatMessageService;
	@Resource
	private ConsumeProjectUser consumeProjectUser;

	@GetMapping("/{userId}")
	public List<ChatMessage> getMsg(@PathVariable Integer userId) {
		return  chatMessageService.getMsgByUserId(userId);
	}

	//随机连接到一位客服
	@GetMapping("/one-customer-service-staff")
	public CustomerServiceStaff getOneCustomerServiceStaff() {
		List<CustomerServiceStaff> customerServiceStaffs = consumeProjectUser.findCustomerServiceStaffs();
		int index = 0;
		Set<Integer> users = ChatServer.getList();
		//在线的
		List<CustomerServiceStaff> data = new ArrayList<>();
		for(CustomerServiceStaff c : customerServiceStaffs){
			if(users.contains(c.getUserId())){
				data.add(c);
				index++;
			}
		}
		Random r = new Random();
		if(index == 0){
			return customerServiceStaffs.get(r.nextInt(customerServiceStaffs.size()));
		}else {
			return data.get(r.nextInt(index));
		}
	}

	@GetMapping("/online-user-id")
	public Set<Integer> getOnlineUserId() {
			return ChatServer.getList();
	}

	@GetMapping("/all-customer-service-staff-id")
	public List<Integer> getAllCustomerServiceStaffId() {
		List<CustomerServiceStaff> customerServiceStaffs = consumeProjectUser.findCustomerServiceStaffs();
		List<Integer> set = new ArrayList<>();
		if(customerServiceStaffs.size()<=0){
			return  null;
		}
		customerServiceStaffs.forEach(item->{
			set.add(item.getUserId());
		});
		return set;
	}

	@GetMapping("/no-read")
	public List<ChatMessageUser> getNoReadMsg() {
		return  chatMessageService.getOnRead(AppUserUtil.getLoginAppUser().getId().intValue());
	}

	@PutMapping("/set-send-read/{sendId}")
	public void updateReadSend(@PathVariable Integer sendId) {
		chatMessageService.updateReadSend(sendId);
	}

	@GetMapping("/all-customer-service-staff")
	public List<CustomerServiceStaff> allOneCustomerServiceStaff() {
		List<CustomerServiceStaff> customerServiceStaffs = consumeProjectUser.findCustomerServiceStaffs();
		Set<Integer> users = ChatServer.getList();
		//在线的
		List<CustomerServiceStaff> data = new ArrayList<>();
		for(CustomerServiceStaff c : customerServiceStaffs){
			if(users.contains(c.getUserId())){
				data.add(c);
			}
		}
		return data;
	}

}
