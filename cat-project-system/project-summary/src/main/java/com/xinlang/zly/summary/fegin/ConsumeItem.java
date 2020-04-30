package com.xinlang.zly.summary.fegin;


import com.xinlang.bean.projectInfo.ItemIndicators;
import com.xinlang.bean.projectInfo.ItemPersonnel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@FeignClient("cat-project-item")
public interface ConsumeItem {
    @PutMapping("/item/indicators")
    void updateIndicator(@RequestBody ItemIndicators indicator);
    @GetMapping("/item/personnel/list")
    ResponseEntity<List<ItemPersonnel>> getPersonnels(@RequestParam Map<String, Object> params);
}
