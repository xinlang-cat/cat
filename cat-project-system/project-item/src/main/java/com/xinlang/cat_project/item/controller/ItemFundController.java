package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.pojo.ItemFund;
import com.xinlang.cat_project.item.service.IItemFundService;
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
@RequestMapping("/item/fund")
public class ItemFundController {

    @Autowired
    private IItemFundService itemFundService;

    @ApiOperation(value = "添加一条资金")
    @LogAnnotation(module = "添加一条资金")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/one")
    public ResponseEntity<Void> saveFund(@RequestBody ItemFund itemFund) {
        itemFundService.save(itemFund);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加多条资金")
    @LogAnnotation(module = "添加多条资金")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<Void> saveFunds(@RequestBody List<ItemFund> itemFunds) {
        itemFundService.saveItemFunds(itemFunds);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询资金")
    @LogAnnotation(module = "查询资金")
    @GetMapping("/list")
    public ResponseEntity<List<ItemFund>> getFundById(@RequestParam Map<String, Object> params){
        List<ItemFund> itemFunds = itemFundService.findListByParams(params,ItemFund.class);
        return ResponseEntity.ok(itemFunds);
    }

    @ApiOperation(value = "修改资金")
    @LogAnnotation(module = "修改资金")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping("/one")
    public ResponseEntity<Void> updateFund(@RequestBody ItemFund itemFund){
        itemFundService.update(itemFund);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "修改多条资金")
    @LogAnnotation(module = "修改多条资金")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping("/multi")
    public ResponseEntity<Void> updateItemFunds(@RequestBody List<ItemFund> itemFunds){
        //先删除所有资金
        itemFundService.deleteTtemFundsByItemId(itemFunds.get(0).getItem_id());
        //重新添加
        itemFundService.saveItemFunds(itemFunds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除资金")
    @LogAnnotation(module = "删除资金")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFund(@PathVariable Integer id){
        itemFundService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
