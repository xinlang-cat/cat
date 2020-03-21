package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemIndicators;
import com.xinlang.cat_project.item.pojo.ItemInformation;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.service.IItemIndicatorsService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ItemIndicators>> saveIndicators(@RequestBody List<ItemIndicators> itemIndicators) {
        itemIndicatorsService.saveIndicators(itemIndicators);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemIndicators);
    }

    @ApiOperation(value = "查询指标")
    @LogAnnotation(module = "查询指标")
    @GetMapping("list")
    public ResponseEntity<List<ItemIndicators>> getIndicators(@RequestParam Map<String, Object> params){
        List<ItemIndicators> indicators = itemIndicatorsService.findListByParams(params,ItemIndicators.class);
        return ResponseEntity.ok(indicators);
    }
}
