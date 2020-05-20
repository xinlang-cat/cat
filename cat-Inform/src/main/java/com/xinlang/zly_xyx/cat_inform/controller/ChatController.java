package com.xinlang.zly_xyx.cat_inform.controller;

import javax.annotation.Resource;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_inform.bean.ChatMessage;
import com.xinlang.zly_xyx.cat_inform.fegin.ConsumeProjectUser;
import com.xinlang.zly_xyx.cat_inform.service.IChatMessageService;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
		Set<Integer> routetab = ChatServer.getList();
		//在线的
		List<CustomerServiceStaff> data = new ArrayList<>();
		for(CustomerServiceStaff c : customerServiceStaffs){
			if(routetab.contains(c.getUserId())){
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


}
