package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemCompany;
import com.xinlang.cat_project.item.service.IItemCompanyService;
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

@RestController
@RequestMapping("/item/company")
public class ItemCompanyController {

    @Autowired
    private IItemCompanyService itemCompanyService;

    @ApiOperation(value = "添加一条联系信息")
    @LogAnnotation(module = "添加一条联系信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("one")
    public ResponseEntity<Void> saveCompany(@RequestBody ItemCompany itemCompany) {
        itemCompanyService.save(itemCompany);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加多条联系信息")
    @LogAnnotation(module = "添加多条联系信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<Void> saveCompanys(@RequestBody List<ItemCompany> itemCompanys) {
        itemCompanyService.saveCompanys(itemCompanys);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询联系信息")
    @LogAnnotation(module = "查询联系信息")
    @GetMapping("/list")
    public ResponseEntity<List<ItemCompany>> getCompanys(@RequestParam Map<String, Object> params){
        List<ItemCompany> itemContent = itemCompanyService.findListByParams(params,ItemCompany.class);
        return ResponseEntity.ok(itemContent);
    }

    @ApiOperation(value = "修改一条联系信息")
    @LogAnnotation(module = "修改一条联系信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping("/one")
    public ResponseEntity<Void> updateCompany(@RequestBody ItemCompany itemCompany){
        itemCompanyService.update(itemCompany);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "修改多条联系信息")
    @LogAnnotation(module = "修改多条联系信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping("/multi")
    public ResponseEntity<Void> updateCompanys(@RequestBody List<ItemCompany> itemCompanys){
        for (ItemCompany itemContent : itemCompanys) {
            itemCompanyService.delete(itemContent.getId());
        }
        itemCompanyService.saveCompanys(itemCompanys);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除联系信息")
    @LogAnnotation(module = "删除联系信息")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id){
        itemCompanyService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
