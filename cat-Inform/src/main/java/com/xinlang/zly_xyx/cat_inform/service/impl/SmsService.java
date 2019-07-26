package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_inform.Mapper.SmsMapper;
import com.xinlang.zly_xyx.cat_inform.bean.Sms;
import com.xinlang.zly_xyx.cat_inform.service.ISmsService;
import com.xinlang.zly_xyx.common.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SmsService implements ISmsService {

	@Value("${aliyun.sign.name1}")
	private String signName;
	@Value("${aliyun.template.code1}")
	private String templateCode;

	@Autowired
	private IAcsClient acsClient;

	@Autowired
	private SmsMapper smsMapper;

	/**
	 * 异步发送阿里云短信
	 * @param sms
	 * @return
	 */
	@Async
	@Override
	public SendSmsResponse sendSmsMsg(Sms sms) {
		if (sms.getSignName() == null) {
			sms.setSignName(this.signName);
		}
		if (sms.getTemplateCode() == null) {
			sms.setTemplateCode(this.templateCode);
		}
		// 阿里云短信官网demo代码
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(sms.getPhone());
		request.setSignName(sms.getSignName());
		request.setTemplateCode(sms.getTemplateCode());
		request.setTemplateParam(sms.getParams());
		request.setOutId(sms.getId().toString());
		SendSmsResponse response = null;
		try {
			response = acsClient.getAcsResponse(request);
			if (response != null) {
				log.info("发送短信结果：code:{}，message:{}，requestId:{}，bizId:{}", response.getCode(), response.getMessage(),
						response.getRequestId(), response.getBizId());

				sms.setCode(response.getCode());
				sms.setMessage(response.getMessage());
				sms.setBizId(response.getBizId());
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		update(sms);
		return response;
	}

	/**
	 * 保存短信记录
	 * @param sms
	 * @param params
	 */
	@Transactional
	@Override
	public void save(Sms sms, Map<String, String> params) {
		if (!CollectionUtils.isEmpty(params)) {
			sms.setParams(JSONObject.toJSONString(params));
		}
		sms.setCreateTime(new Date());
		sms.setUpdateTime(sms.getCreateTime());
		sms.setDay(sms.getCreateTime());

		smsMapper.save(sms);
	}

	/**
	 * 更新
	 * @param sms
	 */
	@Transactional
	@Override
	public void update(Sms sms) {
		sms.setUpdateTime(new Date());
		smsMapper.update(sms);
	}

	/**
	 * 根据Id获取
	 * @param id
	 * @return
	 */
	@Override
	public Sms findById(Long id) {
		return smsMapper.findById(id);
	}

	/**
	 * 分页
	 * @param params
	 * @return
	 */
	@Override
	public Page<Sms> findSms(Map<String, Object> params) {
		int total = smsMapper.count(params);
		@SuppressWarnings("")
		List<Sms> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, true);
			list = smsMapper.findData(params);
		}
		return new Page<>(total, list);
	}

}
