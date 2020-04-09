package com.xinlang.zly.project_user.fegin;

import com.xinlang.bean.projectInfo.ItemPersonnel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("project-item")
public interface ConsumeItemPersonnel {
    @PostMapping ("/item/personnel")
    ResponseEntity<List<ItemPersonnel>> savePersonnels(@RequestBody List<ItemPersonnel> itemPersonnels);
}
