package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemIndicators;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.service.IItemIndicatorsService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 项目考核指标
 */
@RestController
@RequestMapping("/item/indicators")
public class ItemIndicatorsController {
    @Autowired
    private IItemIndicatorsService itemIndicatorsService;

    @ApiOperation(value = "添加多条指标")
    @LogAnnotation(module = "添加多条指标")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemIndicators>> saveTargets(@RequestBody List<ItemIndicators> itemIndicators) {
        itemIndicatorsService.saveIndicators(itemIndicators);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemIndicators);
    }
}
