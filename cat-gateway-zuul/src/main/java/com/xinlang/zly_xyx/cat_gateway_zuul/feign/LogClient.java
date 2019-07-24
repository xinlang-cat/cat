package com.xinlang.zly_xyx.cat_gateway_zuul.feign;

import com.xinlang.zly_xyx.log.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cat-log")
public interface LogClient {
    @PostMapping("/logs-anon/internal")
    void save(@RequestBody Log log);
}
