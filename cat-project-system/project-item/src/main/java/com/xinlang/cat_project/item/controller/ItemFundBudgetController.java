package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.cat_project.item.pojo.ItemFundBudgetVice;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemFundBudgetService;
import com.xinlang.cat_project.item.service.IItemFundBudgetViceService;
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
@RequestMapping("/item/fundBudget")
public class ItemFundBudgetController {
    @Autowired
    private IItemFundBudgetService itemFundBudgetService;
    @Autowired
    private IItemFundBudgetViceService itemFundBudgetViceService;

    @ApiOperation(value = "添加资金预算")
    @LogAnnotation(module = "添加资金预算")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemFundBudget> save(@RequestBody ItemFundBudget itemFundBudget) {
        itemFundBudgetService.save(itemFundBudget);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemFundBudget);
    }

    @ApiOperation(value = "添加多条资金预算")
    @LogAnnotation(module = "添加多条资金预算")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemFundBudget>> saveFundBudgets(@RequestBody List<ItemFundBudget> itemFundBudgets) {
        itemFundBudgetService.saveFundBudgets(itemFundBudgets);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemFundBudgets);
    }

    @ApiOperation(value = "查询资金预算")
    @LogAnnotation(module = "查询资金预算")
    @GetMapping("list")
    public ResponseEntity<List<ItemFundBudget>> getFundBudget(@RequestParam Map<String, Object> params){
        List<ItemFundBudget> FundBudgets = itemFundBudgetService.findListByParams(params,ItemFundBudget.class);
        return ResponseEntity.ok(FundBudgets);
    }

    @ApiOperation(value = "查询资金预算")
    @LogAnnotation(module = "查询资金预算")
    @GetMapping("viceList")
    public ResponseEntity<List<ItemFundBudgetVice>> getFundBudgetVice(@RequestParam Map<String, Object> params){
        List<ItemFundBudgetVice> FundBudgets = itemFundBudgetViceService.findListByParams(params,ItemFundBudgetVice.class);
        return ResponseEntity.ok(FundBudgets);
    }

    @ApiOperation(value = "修改资金预算")
    @LogAnnotation(module = "修改资金预算")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateFundBudget(@RequestBody ItemFundBudget itemFundBudget){
        itemFundBudgetService.update(itemFundBudget);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除资金预算")
    @LogAnnotation(module = "删除资金预算")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundBudget(@PathVariable Integer id){
        itemFundBudgetService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除资金预算")
    @LogAnnotation(module = "通过属性删除资金预算")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteFundBudgets(@RequestBody ItemFundBudget itemFundBudget){
        itemFundBudgetService.deleteByAttribute(itemFundBudget);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
