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
import org.springframework.transaction.annotation.Transactional;
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

    @ApiOperation(value = "添加一条指标")
    @LogAnnotation(module = "添一条加指标")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/one")
    public ResponseEntity<Void> saveTarget(@RequestBody ItemTarget target){
        targetService.save(target);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加多条指标")
    @LogAnnotation(module = "添加多条指标")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<Void> saveContents(@RequestBody List<ItemTarget> itemTargets) {
        targetService.saveTargets(itemTargets);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询指标")
    @LogAnnotation(module = "查询指标")
    @GetMapping("/list")
    public ResponseEntity<List<ItemTarget>> getTargetById(@RequestParam Map<String, Object> params){
        List<ItemTarget> target = targetService.findListByParams(params,ItemTarget.class);
        return ResponseEntity.ok(target);
    }

    @ApiOperation(value = "修改指标")
    @LogAnnotation(module = "修改指标")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping("/one")
    public ResponseEntity<Void> updateTarget(@RequestBody ItemTarget target){
        targetService.update(target);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "修改多条指标")
    @LogAnnotation(module = "修改多条指标")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping("/multi")
    public ResponseEntity<Void> updateContents(@RequestBody List<ItemTarget> itemContents){
        for (ItemTarget itemContent : itemContents) {
            targetService.delete(itemContent.getId());
        }
        targetService.saveTargets(itemContents);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除指标")
    @LogAnnotation(module = "删除指标")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable Integer id){
        targetService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
