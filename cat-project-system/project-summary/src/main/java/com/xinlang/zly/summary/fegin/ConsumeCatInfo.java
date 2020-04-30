package com.xinlang.zly.summary.fegin;

import com.xinlang.zly_xyx.message.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cat-inform")
public interface ConsumeCatInfo {

    @PostMapping("/message")
    Message save(@RequestBody Message message);

}
