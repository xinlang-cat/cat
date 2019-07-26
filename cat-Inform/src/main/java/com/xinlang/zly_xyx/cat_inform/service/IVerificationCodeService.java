package com.xinlang.zly_xyx.cat_inform.service;

import com.xinlang.zly_xyx.cat_inform.bean.VerificationCode;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
public interface IVerificationCodeService {

    VerificationCode generateCode(String phone);

    String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second);
}
