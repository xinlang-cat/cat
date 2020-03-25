package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemSchedulingService;
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
@RequestMapping("/item/scheduling")
public class ItemSchedulingController {
    @Autowired
    private IItemSchedulingService itemSchedulingService;

    @ApiOperation(value = "添加进度安排")
    @LogAnnotation(module = "添加进度安排")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemScheduling> save(@RequestBody ItemScheduling itemScheduling) {
        itemSchedulingService.save(itemScheduling);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemScheduling);
    }

    @ApiOperation(value = "添加多条进度安排")
    @LogAnnotation(module = "添加多条进度安排")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemScheduling>> saveSchedulings(@RequestBody List<ItemScheduling> itemSchedulings) {
        itemSchedulingService.saveSchedulings(itemSchedulings);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemSchedulings);
    }

    @ApiOperation(value = "查询进度安排")
    @LogAnnotation(module = "查询进度安排")
    @GetMapping("list")
    public ResponseEntity<List<ItemScheduling>> getScheduling(@RequestParam Map<String, Object> params){
        List<ItemScheduling> schedulings = itemSchedulingService.findListByParams(params,ItemScheduling.class);
        return ResponseEntity.ok(schedulings);
    }

    @ApiOperation(value = "修改进度安排")
    @LogAnnotation(module = "修改进度安排")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateScheduling(@RequestBody ItemScheduling itemScheduling){
        itemSchedulingService.update(itemScheduling);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除进度安排")
    @LogAnnotation(module = "删除进度安排")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable Integer id){
        itemSchedulingService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除进度安排")
    @LogAnnotation(module = "通过属性删除进度安排")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteSchedulings(@RequestBody ItemScheduling itemScheduling){
        itemSchedulingService.deleteByAttribute(itemScheduling);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
