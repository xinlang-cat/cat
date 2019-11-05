package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.pojo.ItemFund;
import com.xinlang.cat_project.item.service.IItemFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/fund")
public class ItemFundController {

    @Autowired
    private IItemFundService itemFundService;

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
    @GetMapping("/{id}")
    public ResponseEntity<ItemFund> gatqueryFundById(@PathVariable Integer id){

        ItemFund itemFund = itemFundService.queryFundById(id);
        return ResponseEntity.ok(itemFund);
    }

    /**
     * 获取项目的所有资金
     * @param Iid
     * @return
     */
    @GetMapping("/all/{Iid}")
    public ResponseEntity<List<ItemFund>> gatqueryFundByIId(@PathVariable Integer Iid){

        List<ItemFund> itemFund = itemFundService.queryFundByIId(Iid);
        return ResponseEntity.ok(itemFund);
    }

    /**
     * 更改
     * @param itemFund
     * @return
     */
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFund(@PathVariable Integer id){
        itemFundService.deleteFund(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
