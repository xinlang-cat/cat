package com.xinlang.zly_xyx.cat_inform.controller;

import com.xinlang.zly_xyx.cat_common.utils.PhoneUtil;
import com.xinlang.zly_xyx.cat_inform.bean.Sms;
import com.xinlang.zly_xyx.cat_inform.bean.VerificationCode;
import com.xinlang.zly_xyx.cat_inform.service.ISmsService;
import com.xinlang.zly_xyx.cat_inform.service.IVerificationCodeService;
import com.xinlang.zly_xyx.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@RestController
public class SmsController {
    @Autowired
    private IVerificationCodeService verificationCodeService;
    @Autowired
    private ISmsService smsService;


    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @PostMapping(value = "/inform-anon/codes", params = { "phone" })
    public VerificationCode sendSmsVerificationCode(String phone) {
        if (!PhoneUtil.checkPhone(phone)) {
            throw new IllegalArgumentException("手机号格式不正确");
        }

        VerificationCode verificationCode = verificationCodeService.generateCode(phone);

        return verificationCode;
    }

    /**
     * 根据验证码和key获取手机号
     *
     * @param key
     * @param code
     * @param delete
     *            是否删除key
     * @param second
     *            不删除时，可重置过期时间（秒）
     * @return
     */
    @GetMapping(value = "/inform-anon/internal/phone", params = { "key", "code" })
    public String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second) {
        return verificationCodeService.matcheCodeAndGetPhone(key, code, delete, second);
    }

    /**
     * 查询短信发送记录
     *
     * @param params
     * @return
     */
    @PreAuthorize("hasAuthority('sms:query')")
    @GetMapping("/sms")
    public Page<Sms> findSms(@RequestParam Map<String, Object> params) {
        return smsService.findSms(params);
    }
}
