package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemFundBudgetService;
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

    @ApiOperation(value = "添加多条资金预算")
    @LogAnnotation(module = "添加多条资金预算")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemFundBudget>> saveFundBudgets(@RequestBody List<ItemFundBudget> itemFundBudgets) {
        itemFundBudgetService.saveFundBudgets(itemFundBudgets);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemFundBudgets);
    }

    @ApiOperation(value = "查询进度安排")
    @LogAnnotation(module = "查询进度安排")
    @GetMapping("list")
    public ResponseEntity<List<ItemFundBudget>> getFundBudget(@RequestParam Map<String, Object> params){
        List<ItemFundBudget> FundBudgets = itemFundBudgetService.findListByParams(params,ItemFundBudget.class);
        return ResponseEntity.ok(FundBudgets);
    }
}
