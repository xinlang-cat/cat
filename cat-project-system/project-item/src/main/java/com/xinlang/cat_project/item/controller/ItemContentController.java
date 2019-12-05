package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.service.IItemContentService;
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
 * @author 梁应昌
 * 2019/10/31
 */
@RestController
@RequestMapping("/item/content")
public class ItemContentController {

    @Autowired
    private IItemContentService iItemContentService;

    @ApiOperation(value = "添加一条主要研究内容")
    @LogAnnotation(module = "添加一条主要研究内容")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("one")
    public ResponseEntity<Void> saveContent(@RequestBody ItemContent itemContent) {
        iItemContentService.save(itemContent);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加多条主要研究内容")
    @LogAnnotation(module = "添加多条主要研究内容")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("multi")
    public ResponseEntity<Void> saveContents(@RequestBody List<ItemContent> itemContents) {
        iItemContentService.saveContents(itemContents);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询主要研究内容")
    @LogAnnotation(module = "查询主要研究内容")
    @GetMapping("/list")
    public ResponseEntity<List<ItemContent>> getPlanById(@RequestParam Map<String, Object> params){
        List<ItemContent> itemContent = iItemContentService.findListByParams(params,ItemContent.class);
        return ResponseEntity.ok(itemContent);
    }

    @ApiOperation(value = "修改一条主要研究内容")
    @LogAnnotation(module = "修改一条条主要研究内容")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping("one")
    public ResponseEntity<Void> updateContent(@RequestBody ItemContent itemContent){
        iItemContentService.update(itemContent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "修改多条主要研究内容")
    @LogAnnotation(module = "修改多条主要研究内容")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping("multi")
    public ResponseEntity<Void> updateContents(@RequestBody List<ItemContent> itemContents){
        for (ItemContent itemContent : itemContents) {
            iItemContentService.delete(itemContent.getId());
        }
        iItemContentService.saveContents(itemContents);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除主要研究内容")
    @LogAnnotation(module = "删除主要研究内容")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Integer id){
        iItemContentService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
