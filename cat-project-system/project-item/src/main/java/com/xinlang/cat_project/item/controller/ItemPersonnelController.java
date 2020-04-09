package com.xinlang.cat_project.item.controller;

import com.xinlang.bean.projectInfo.ItemPersonnel;
import com.xinlang.cat_project.item.pojo.ItemPersonnelVice;
import com.xinlang.cat_project.item.service.IItemPersonnelService;
import com.xinlang.cat_project.item.service.IItemPersonnelViceService;
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
@RequestMapping("/item/personnel")
public class ItemPersonnelController {
    @Autowired
    private IItemPersonnelService itemPersonnelService;

    @Autowired
    private IItemPersonnelViceService itemPersonnelViceService;

    @ApiOperation(value = "添加项目人员")
    @LogAnnotation(module = "添加项目人员")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemPersonnel> save(@RequestBody ItemPersonnel itemPersonnel) {
        itemPersonnelService.save(itemPersonnel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemPersonnel);
    }

    @ApiOperation(value = "添加多条项目人员")
    @LogAnnotation(module = "添加多条项目人员")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemPersonnel>> savePersonnels(@RequestBody List<ItemPersonnel> itemPersonnels) {
        itemPersonnelService.savePersonnels(itemPersonnels);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemPersonnels);
    }

    @ApiOperation(value = "查询项目人员")
    @LogAnnotation(module = "查询项目人员")
    @GetMapping("list")
    public ResponseEntity<List<ItemPersonnel>> getPersonnels(@RequestParam Map<String, Object> params){
        List<ItemPersonnel> personnels = itemPersonnelService.findListByParams(params,ItemPersonnel.class);
        return ResponseEntity.ok(personnels);
    }

    @ApiOperation(value = "查询项目人员")
    @LogAnnotation(module = "查询项目人员")
    @GetMapping("viceList")
    public ResponseEntity<List<ItemPersonnelVice>> getPersonnelsVice(@RequestParam Map<String, Object> params){
        List<ItemPersonnelVice> personnels = itemPersonnelViceService.findListByParams(params,ItemPersonnelVice.class);
        return ResponseEntity.ok(personnels);
    }

    @ApiOperation(value = "修改项目人员")
    @LogAnnotation(module = "修改项目人员")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updatePersonnel(@RequestBody ItemPersonnel itemPersonnel){
        itemPersonnelService.update(itemPersonnel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除项目人员")
    @LogAnnotation(module = "删除项目人员")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Integer id){
        itemPersonnelService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除项目人员")
    @LogAnnotation(module = "通过属性删除项目人员")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deletePersonnels(@RequestBody ItemPersonnel itemPersonnel){
        itemPersonnelService.deleteByAttribute(itemPersonnel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
