package com.xinlang.cat_project.item.controller;

import com.xinlang.bean.projectInfo.ItemIndicators;
import com.xinlang.cat_project.item.pojo.ItemIndicatorsVice;
import com.xinlang.cat_project.item.service.IItemIndicatorsService;
import com.xinlang.cat_project.item.service.IItemIndicatorsViceService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 项目考核指标
 */
@RestController
@RequestMapping("/item/indicators")
public class ItemIndicatorsController {
    @Autowired
    private IItemIndicatorsService itemIndicatorsService;

    @Autowired
    private IItemIndicatorsViceService itemIndicatorsViceService;

    @ApiOperation(value = "添加指标")
    @LogAnnotation(module = "添加指标")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemIndicators> save(@RequestBody ItemIndicators itemIndicator) {
        itemIndicatorsService.save(itemIndicator);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemIndicator);
    }

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

    @ApiOperation(value = "查询指标")
    @LogAnnotation(module = "查询指标")
    @GetMapping("viceList")
    public ResponseEntity<List<ItemIndicatorsVice>> getIndicatorsVice(@RequestParam Map<String, Object> params){
        List<ItemIndicatorsVice> indicators = itemIndicatorsViceService.findListByParams(params,ItemIndicatorsVice.class);
        return ResponseEntity.ok(indicators);
    }

    @ApiOperation(value = "修改指标")
    @LogAnnotation(module = "修改指标")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateIndicators(@RequestBody ItemIndicators indicator){
        itemIndicatorsService.update(indicator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除指标")
    @LogAnnotation(module = "删除指标")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndicator(@PathVariable Integer id){
        itemIndicatorsService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除指标")
    @LogAnnotation(module = "通过属性删除指标")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteIndicators(@RequestBody ItemIndicators indicator){
        itemIndicatorsService.deleteByAttribute(indicator);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/ids")
    @ApiOperation(value = "根据ids查询指标")
    public List<ItemIndicators> findByIds(@RequestParam("ids") Set<Integer> ids) {
        System.err.println(ids);
        return itemIndicatorsService.getByIds(new ArrayList<>(ids));
    }
}
