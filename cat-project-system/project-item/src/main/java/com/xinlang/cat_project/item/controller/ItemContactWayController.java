package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemContactWay;
import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.service.IItemContactWayService;
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
@RequestMapping("/item/contactWay")
public class ItemContactWayController {
    @Autowired
    private IItemContactWayService itemContactWayService;

    @ApiOperation(value = "添加各方联系方式")
    @LogAnnotation(module = "添加各方联系方式")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemContactWay> save(@RequestBody ItemContactWay itemContactWay) {
        itemContactWayService.save(itemContactWay);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemContactWay);
    }

    @ApiOperation(value = "添加多条各方联系方式")
    @LogAnnotation(module = "添加多条各方联系方式")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemContactWay>> saveContactWays(@RequestBody List<ItemContactWay> itemContactWays) {
        itemContactWayService.saveContactWays(itemContactWays);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemContactWays);
    }
    @ApiOperation(value = "查询各方联系方式")
    @LogAnnotation(module = "查询各方联系方式")
    @GetMapping("list")
    public ResponseEntity<List<ItemContactWay>> getContactWay(@RequestParam Map<String, Object> params){
        List<ItemContactWay> ContactWays = itemContactWayService.findListByParams(params,ItemContactWay.class);
        return ResponseEntity.ok(ContactWays);
    }

    @ApiOperation(value = "修改各方联系方式")
    @LogAnnotation(module = "修改各方联系方式")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ItemContactWay itemContactWay){
        itemContactWayService.update(itemContactWay);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除各方联系方式")
    @LogAnnotation(module = "删除各方联系方式")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        itemContactWayService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除各方联系方式")
    @LogAnnotation(module = "通过属性删除各方联系方式")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteByAttribute(@RequestBody ItemContactWay itemContactWay){
        itemContactWayService.deleteByAttribute(itemContactWay);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
