package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemPersonnel;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemPersonnelService;
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

    @ApiOperation(value = "添加多条项目人员")
    @LogAnnotation(module = "添加多条项目人员")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemPersonnel>> saveTargets(@RequestBody List<ItemPersonnel> itemPersonnels) {
        itemPersonnelService.savePersonnels(itemPersonnels);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemPersonnels);
    }

    @ApiOperation(value = "查询项目人员")
    @LogAnnotation(module = "查询项目人员")
    @GetMapping("list")
    public ResponseEntity<List<ItemPersonnel>> getScheduling(@RequestParam Map<String, Object> params){
        List<ItemPersonnel> personnels = itemPersonnelService.findListByParams(params,ItemPersonnel.class);
        return ResponseEntity.ok(personnels);
    }
}
