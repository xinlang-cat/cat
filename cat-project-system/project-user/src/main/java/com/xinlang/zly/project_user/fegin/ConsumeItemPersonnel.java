package com.xinlang.zly.project_user.fegin;

import com.xinlang.bean.projectInfo.ItemPersonnel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cat-project-item")
public interface ConsumeItemPersonnel {
    @PostMapping ("/item/personnel")
    void savePersonnels(@RequestBody ItemPersonnel itemPersonnel);
}
