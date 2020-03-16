package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemInformation;
import com.xinlang.cat_project.item.service.IItemInformationService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/item/information")
public class ItemInformationController {

    @Autowired
    private IItemInformationService itemInformationService;

    @ApiOperation(value = "添加项目信息")
    @LogAnnotation(module = "添加项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemInformation> saveItem(@RequestBody ItemInformation itemInformation) {
        System.out.println(itemInformation);
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemInformation.setEdit_user_id(userId);
        itemInformation.setEdit_date(new Date());
        itemInformationService.save(itemInformation);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemInformation);
    }

}
