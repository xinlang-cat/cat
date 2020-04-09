package com.xinlang.zly.summary.fegin;


import com.xinlang.bean.projectInfo.ItemIndicators;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("project-item")
public interface ConsumeIndicators {
    @PutMapping("/item/indicators")
    void updateIndicator(@RequestBody ItemIndicators indicator);
}
