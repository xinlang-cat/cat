package com.xinlang.zly.summary.fegin;

import com.xinlang.bean.project_user.ProjectUserItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("project-user")
public interface ConsumeItemUser {
    @GetMapping("/item/expert")
    List<ProjectUserItem> findExpertByItemId(@RequestParam Integer itemId);
}
