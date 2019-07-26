package com.xinlang.zly_xyx.cat_inform.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.xinlang.zly_xyx.cat_inform.bean.Sms;
import com.xinlang.zly_xyx.common.Page;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
public interface ISmsService {

    void save(Sms sms, Map<String, String> params);

    void update(Sms sms);

    Sms findById(Long id);

    Page<Sms> findSms(Map<String, Object> params);

    /**
     * 发送短信
     */
    SendSmsResponse sendSmsMsg(Sms sms);
}
