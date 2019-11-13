package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemBudget;
import com.xinlang.cat_project.item.service.IItemBudgetService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/budget")
public class ItemBudgetController {

    @Autowired
    private IItemBudgetService itemBudgetService;

    @ApiOperation(value = "添加项目预算")
    @LogAnnotation(module = "添加项目预算")
    @PreAuthorize("hasAnyAuthority('project:budget:svae')")
    @PostMapping
    public ResponseEntity<Void> saveBudget(@RequestBody ItemBudget itemBudget) {
        itemBudgetService.saveBudget(itemBudget);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条资金预算
     * @param id
     * @return
     */
    @ApiOperation(value = "获取一天项目预算信息，id必填")
    @LogAnnotation(module = "获取一条项目预算")
    @GetMapping("/{id}")
    public ResponseEntity<ItemBudget> getBudgetById(@PathVariable Integer id){

        ItemBudget itemBudget = itemBudgetService.queryBudgetById(id);
        return ResponseEntity.ok(itemBudget);
    }

    /**
     * 获取资金的所有预算
     * @param Fid 资金id
     * @return
     */
    @ApiOperation(value = "获取资金的所有预算，fund_id必填")
    @LogAnnotation(module = "获取资金的所有预算")
    @GetMapping("/group/{Fid}")
    public ResponseEntity<List<ItemBudget>> getBudgetGroupByFid(@PathVariable Integer Fid){

        List<ItemBudget> itemBudgets = itemBudgetService.queryBudgetByFid(Fid);
        return ResponseEntity.ok(itemBudgets);
    }

    /**
     * 获取项目的所有预算
     * @param Iid 项目id
     * @return
     */
    @ApiOperation(value = "获取项目的所有预算，item_id必填")
    @LogAnnotation(module = "获取项目的所有预算")
    @GetMapping("/all/{Iid}")
    public ResponseEntity<List<ItemBudget>> getBudgetAllByIid(@PathVariable Integer Iid){

        List<ItemBudget> itemBudgets = itemBudgetService.queryBudgetByIid(Iid);
        return ResponseEntity.ok(itemBudgets);
    }

    /**
     * 更改
     * @param itemBudget
     * @return
     */
    @ApiOperation(value = "修改项目预算，id必填")
    @LogAnnotation(module = "修改项目预算")
    @PreAuthorize("hasAnyAuthority('project:budget:update')")
    @PutMapping
    public ResponseEntity<Void> updatePlan(@RequestBody ItemBudget itemBudget){
        itemBudgetService.updateBudget(itemBudget);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除项目预算，id必填")
    @LogAnnotation(module = "删除项目预算")
    @PreAuthorize("hasAnyAuthority('project:budget:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Integer id){
        itemBudgetService.deleteBudget(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
