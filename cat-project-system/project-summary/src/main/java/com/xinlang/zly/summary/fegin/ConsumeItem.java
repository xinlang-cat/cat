package com.xinlang.zly.summary.fegin;


import com.xinlang.bean.projectInfo.ItemIndicators;
import com.xinlang.bean.projectInfo.ItemInformation;
import com.xinlang.bean.projectInfo.ItemPersonnel;
import com.xinlang.bean.projectInfo.ItemTermination;
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
    @GetMapping("/item/information/list")
    ResponseEntity<List<ItemInformation>> getItemById(@RequestParam Map<String, Object> params);
    @GetMapping("/item/termination/list")
    ResponseEntity<List<ItemTermination>> findListByParams(@RequestParam Map<String, Object> params);
    @PutMapping("/item/termination")
    void update(@RequestBody ItemTermination itemTermination);
}
