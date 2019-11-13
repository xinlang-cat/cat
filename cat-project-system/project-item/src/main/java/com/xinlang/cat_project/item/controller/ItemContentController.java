package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.service.IItemContentService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 梁应昌
 * 2019/10/31
 */
@RestController
@RequestMapping("/item/content")
public class ItemContentController {

    @Autowired
    private IItemContentService planService;

    /**
     * 添加内容
     * @param itemContent
     * @return
     */
    @ApiOperation(value = "添加主要研究内容信息")
    @LogAnnotation(module = "添加主要研究内容信息")
    @PreAuthorize("hasAnyAuthority('project:content:save')")
    @PostMapping
    public ResponseEntity<Void> savePlan(@RequestBody ItemContent itemContent) {
        planService.savePlan(itemContent);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条内容
     * @param id 内容id
     * @return
     */
    @ApiOperation(value = "获取一条主要研究内容信息，id必填")
    @LogAnnotation(module = "获取一条主要研究内容信息")
    @GetMapping("/{id}")
    public ResponseEntity<ItemContent> getPlanById(@PathVariable Integer id){

        ItemContent itemContent = planService.queryPlanById(id);
        return ResponseEntity.ok(itemContent);
    }

    /**
     * 获取当前项目所有内容
     * @param Tid 项目id
     * @return
     */
    @ApiOperation(value = "获取所有主要研究内容信息，item_id必填")
    @LogAnnotation(module = "获取所有主要研究内容信息")
    @GetMapping("/all/{Tid}")
    public ResponseEntity<List<ItemContent>> getPlanByTid(@PathVariable Integer Tid){

        List<ItemContent> itemContent = planService.queryPlanByTId(Tid);
        return ResponseEntity.ok(itemContent);
    }

    /**
     * 更改
     * @param itemContent
     * @return
     */
    @ApiOperation(value = "修改主要研究内容信息，id必填")
    @LogAnnotation(module = "修改条主要研究内容信息")
    @PreAuthorize("hasAnyAuthority('project:content:update')")
    @PutMapping
    public ResponseEntity<Void> updatePlan(@RequestBody ItemContent itemContent){
        planService.updatePlan(itemContent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除主要研究内容信息，id必填")
    @LogAnnotation(module = "删除主要研究内容信息")
    @PreAuthorize("hasAnyAuthority('project:content:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Integer id){
        planService.deletePlan(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
