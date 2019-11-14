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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/fund")
public class ItemFundController {

    @Autowired
    private IItemFundService itemFundService;

    @ApiOperation(value = "添加资金信息")
    @LogAnnotation(module = "添加资金信息")
    @PreAuthorize("hasAnyAuthority('project:fund:save')")
    @PostMapping
    public ResponseEntity<Void> saveFund(@RequestBody ItemFund itemFund) {
        itemFundService.saveFund(itemFund);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条资金
     * @param id
     * @return
     */
    @ApiOperation(value = "获取一条资金信息，id必填")
    @LogAnnotation(module = "获取一条资金信息")
    @GetMapping("/{id}")
    public ResponseEntity<ItemFund> getFundById(@PathVariable Integer id){

        ItemFund itemFund = itemFundService.queryFundById(id);
        return ResponseEntity.ok(itemFund);
    }

    /**
     * 获取项目的所有资金
     * @param Iid
     * @return
     */
    @ApiOperation(value = "获取所有资金信息，item_id必填")
    @LogAnnotation(module = "获取项目所有资金信息")
    @GetMapping("/all/{Iid}")
    public ResponseEntity<List<ItemFund>> getFundByIId(@PathVariable Integer Iid){

        List<ItemFund> itemFund = itemFundService.queryFundByIId(Iid);
        return ResponseEntity.ok(itemFund);
    }

    /**
     * 更改
     * @param itemFund
     * @return
     */
    @ApiOperation(value = "修改资金信息，id必填")
    @LogAnnotation(module = "修改资金信息")
    @PreAuthorize("hasAnyAuthority('project:fund:update')")
    @PutMapping
    public ResponseEntity<Void> updateFund(@RequestBody ItemFund itemFund){
        itemFundService.updateFund(itemFund);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除资金信息，id必填")
    @LogAnnotation(module = "删除资金信息")
    @PreAuthorize("hasAnyAuthority('project:fund:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFund(@PathVariable Integer id){
        itemFundService.deleteFund(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
