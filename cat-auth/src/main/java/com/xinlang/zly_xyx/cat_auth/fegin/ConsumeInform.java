package com.xinlang.zly_xyx.cat_auth.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@FeignClient("cat-inform")
public interface ConsumeInform {
    @GetMapping(value = "/inform-anon/internal/phone",params = {"key","code"})
    String matcheCodeAndGetPhone(@RequestParam("key") String kry,@RequestParam("code") String code,
                                 @RequestParam(value = "delete",required = false)Boolean delete,
                                 @RequestParam(value = "second",required = false)Integer second);
}
