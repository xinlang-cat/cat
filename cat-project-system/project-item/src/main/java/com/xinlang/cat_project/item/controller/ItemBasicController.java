package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.service.IItemBasicService;
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
 * @author 梁应昌
 * 2019/9/27
 */

@RestController
@RequestMapping("/item")
public class ItemBasicController {

    @Autowired
    private IItemBasicService itemService;

    /**
     * 查询项目，分页查询
     * @param page 页数
     * @param rows 行数
     * @param sortBy 排序依据 (预留，暂时没用到)
     * @param desc 排序 (预留，暂时没用到)
     * @param params 参数
     * @return
     */
    @LogAnnotation(module = "获取项目列表")
    @GetMapping
    public ResponseEntity<PageResult<ItemBasic>> getItemAll(@RequestParam(value = "start", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "length", defaultValue = "10") Integer rows,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                            @RequestParam(required = false) Map<String, Object> params){

        if (page==0){
            page++;
        }else {
            page/=10;
            page++;
        }
        PageResult<ItemBasic> result = itemService.queryList(page,rows,sortBy,desc,params);
        return ResponseEntity.ok(result);
    }

    /**
     * 新增项目
     * @param basic
     * @return
     */
    @ApiOperation(value = "添加项目基础信息")
    @LogAnnotation(module = "添加项目基础信息")
    @PreAuthorize("hasAnyAuthority('project:basic:save')")
    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestBody ItemBasic basic) {
        itemService.saveItem(basic);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取一条项目数据
     * @param id
     * @return
     */
    @ApiOperation(value = "查询一条项目基础信息")
    @LogAnnotation(module = "查询一条项目基础信息")
    @GetMapping("/{id}")
    public ResponseEntity<ItemBasic> getItemById(@PathVariable Integer id){

        ItemBasic basic = itemService.queryItemById(id);
        return ResponseEntity.ok(basic);
    }

    /**
     * 获取当前用户的公司项目数据
     * @return
     */
    @ApiOperation(value = "查询一条项目基础信息")
    @LogAnnotation(module = "查询一条项目基础信息")
    @GetMapping("/company")
    public ResponseEntity<List<ItemBasic>> getCompanyItem(){

        List<ItemBasic> basic = itemService.queryCompanyItem();
        return ResponseEntity.ok(basic);
    }

    /**
     * 更改
     * @param basic
     * @return
     */
    @ApiOperation(value = "修改项目基础信息，id必填")
    @LogAnnotation(module = "修改项目基础信息")
    @PreAuthorize("hasAnyAuthority('project:basic:update')")
    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemBasic basic){
        itemService.updateItem(basic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除项目基础信息，id必填")
    @LogAnnotation(module = "删除项目基础信息")
    @PreAuthorize("hasAnyAuthority('project:basic:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 废弃
     * @param id
     * @return
     */
    @ApiOperation(value = "废弃项目，id必填")
    @LogAnnotation(module = "废弃项目")
    @PreAuthorize("hasAnyAuthority('project:basic:discard')")
    @PutMapping("/discard/{id}")
    public ResponseEntity<Void> discardItem(@PathVariable Integer id){
        itemService.discardItem(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
