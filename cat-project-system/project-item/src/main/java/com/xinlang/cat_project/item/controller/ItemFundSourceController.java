package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemFundSourceService;
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
@RequestMapping("/item/fundSource")
public class ItemFundSourceController {
    @Autowired
    private IItemFundSourceService itemFundSourceService;

    @ApiOperation(value = "添加资金来源")
    @LogAnnotation(module = "添加资金来源")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemFundSource> save(@RequestBody ItemFundSource itemFundSource) {
        itemFundSourceService.save(itemFundSource);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemFundSource);
    }

    @ApiOperation(value = "查询经费来源")
    @LogAnnotation(module = "查询经费来源")
    @GetMapping("list")
    public ResponseEntity<List<ItemFundSource>> getFundSource(@RequestParam Map<String, Object> params){
        List<ItemFundSource> fundSources = itemFundSourceService.findListByParams(params,ItemFundSource.class);
        return ResponseEntity.ok(fundSources);
    }

    @ApiOperation(value = "修改经费来源")
    @LogAnnotation(module = "修改经费来源")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ItemFundSource itemFundSource){
        itemFundSourceService.update(itemFundSource);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除经费来源")
    @LogAnnotation(module = "删除经费来源")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        itemFundSourceService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除经费来源")
    @LogAnnotation(module = "通过属性删除经费来源")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteByAttribute(@RequestBody ItemFundSource itemFundSource){
        itemFundSourceService.deleteByAttribute(itemFundSource);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
