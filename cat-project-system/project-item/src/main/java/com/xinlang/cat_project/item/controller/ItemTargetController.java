package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
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
 * 指标
 */
@RestController
@RequestMapping("/item/target")
public class ItemTargetController {

    @Autowired
    private IItemTargetService targetService;

    /**
     * 新增指标
     * @param target
     * @return
     */
    @ApiOperation(value = "添加指标信息")
    @LogAnnotation(module = "添加指标信息")
    @PreAuthorize("hasAnyAuthority('project:target:save')")
    @PostMapping
    public ResponseEntity<Void> saveTarget(@RequestBody ItemTarget target){
        targetService.saveTarget(target);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条指标
     * @param id
     * @return
     */
    @ApiOperation(value = "获取一条指标信息")
    @LogAnnotation(module = "获取一条指标信息")
    @GetMapping("/{id}")
    public ResponseEntity<ItemTarget> getTargetById(@PathVariable Integer id){

        ItemTarget target = targetService.queryTargetById(id);
        return ResponseEntity.ok(target);
    }

    /**
     * 获取当前内容全部指标
     * @param Cid 内容id
     * @return
     */
    @ApiOperation(value = "获取主要研究内容的所有指标信息")
    @LogAnnotation(module = "获取主要研究内容的所有指标信息")
    @GetMapping("/group/{Cid}")
    public ResponseEntity<List<ItemTarget>> getTargetByCid(@PathVariable Integer Cid){

        List<ItemTarget> target = targetService.queryTargetByCId(Cid);
        return ResponseEntity.ok(target);
    }

    /**
     * 获取项目全部指标
     * @param itemId 项目id
     * @return
     */
    @ApiOperation(value = "获取项目所有指标信息")
    @LogAnnotation(module = "获取项目所有指标信息")
    @GetMapping("/all/{itemId}")
    public ResponseEntity<List<ItemTarget>> getTargetByItemId(@PathVariable Integer itemId){

        List<ItemTarget> target = targetService.queryTargetByItemId(itemId);
        return ResponseEntity.ok(target);
    }

    /**
     * 获取当前用户相关指标
     * @return
     */
    @ApiOperation(value = "获取当前用户相关指标")
    @LogAnnotation(module = "获取当前用户相关指标")
    @GetMapping("/my/{itemId}")
    public ResponseEntity<List<ItemTarget>> getTargetByUserId(@PathVariable Integer itemId){

        List<ItemTarget> target = targetService.queryTargetByUserId(itemId);
        ItemTarget a =new ItemTarget();
        a.setId(1);
        a.setTarget("测试");
        target.add(a);
        return ResponseEntity.ok(target);
    }

    /**
     * 更改
     * @param target
     * @return
     */
    @ApiOperation(value = "修改指标信息，id必填")
    @LogAnnotation(module = "修改指标信息")
    @PreAuthorize("hasAnyAuthority('project:target:update')")
    @PutMapping
    public ResponseEntity<Void> updateTarget(@RequestBody ItemTarget target){
        targetService.updateTarget(target);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除指标信息，id必填")
    @LogAnnotation(module = "删除指标信息")
    @PreAuthorize("hasAnyAuthority('project:target:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable Integer id){
        targetService.deleteTarget(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
