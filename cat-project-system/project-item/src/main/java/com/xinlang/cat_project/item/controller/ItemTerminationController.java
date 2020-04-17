package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemFundSourceVice;
import com.xinlang.cat_project.item.pojo.ItemTermination;
import com.xinlang.cat_project.item.service.IItemFundSourceService;
import com.xinlang.cat_project.item.service.IItemFundSourceViceService;
import com.xinlang.cat_project.item.service.IItemTerminationService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item/termination")
public class ItemTerminationController {
    @Autowired
    private IItemTerminationService itemTerminationService;

    @ApiOperation(value = "添加终止申请")
    @LogAnnotation(module = "添加终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemTermination> save(@RequestBody ItemTermination itemTermination) {
        itemTerminationService.save(itemTermination);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemTermination);
    }

    @ApiOperation(value = "查询终止申请")
    @LogAnnotation(module = "查询终止申请")
    @GetMapping("list")
    public ResponseEntity<List<ItemTermination>> getFundSource(@RequestParam Map<String, Object> params){
        List<ItemTermination> itemTermination = itemTerminationService.findListByParams(params,ItemTermination.class);
        return ResponseEntity.ok(itemTermination);
    }

    @ApiOperation(value = "修改终止申请")
    @LogAnnotation(module = "修改终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ItemTermination itemTermination){
        itemTerminationService.update(itemTermination);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除终止申请")
    @LogAnnotation(module = "删除终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        itemTerminationService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除终止申请")
    @LogAnnotation(module = "通过属性删除终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteByAttribute(@RequestBody ItemTermination itemTermination){
        itemTerminationService.deleteByAttribute(itemTermination);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}