package com.xinlang.zly_xyx.cat_auth.fegin;

import com.xinlang.zly_xyx.log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@FeignClient("cat-log")
public interface ConsumeLog {
    @PostMapping("/logs-anon/internal")
    void save(@RequestBody Log log);
}
