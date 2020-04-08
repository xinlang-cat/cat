package com.xinlang.zly.summary.fegin;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("project-item")
public interface ConsumeIndicators {
    @PutMapping("/item/indicators")
    void updateIndicator(@RequestParam Integer id,@RequestParam String plan);
}
