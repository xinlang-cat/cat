package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.fegin.ConsumeUser;
import com.xinlang.cat_project.item.pojo.ItemFundUse;
import com.xinlang.cat_project.item.service.IItemFundUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item/fund/use")
public class ItemFundUseController {

    @Autowired
    private IItemFundUseService itemFundUseService;

    @Autowired
    private ConsumeUser consumeUser;

    @PostMapping
    public ResponseEntity<Void> saveFundUse(@RequestBody ItemFundUse itemFundUse) {
        itemFundUseService.saveFundUse(itemFundUse);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条使用数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemFundUse> getFundById(@PathVariable Integer id){

        ItemFundUse itemFundUse = itemFundUseService.queryFundUseById(id);
        return ResponseEntity.ok(itemFundUse);
    }

    /**
     * 获取预算的所有使用数据
     * @param Bid 预算id
     * @return
     */
    @GetMapping("/info/{Bid}")
    public ResponseEntity<List<ItemFundUse>> getFundUseByBId(@PathVariable Integer Bid){

        List<ItemFundUse> itemFundUses = itemFundUseService.queryFundUseByBId(Bid);
        return ResponseEntity.ok(itemFundUses);
    }

    /**
     * 获取当前用户所保存的所有数据
     * @param
     * @return
     */
    @GetMapping("/my/{Iid}")
    public ResponseEntity<List<ItemFundUse>> getFundUseByItemIdAndUserId(@PathVariable Integer Iid){

        int UserTd = consumeUser.getLoginAppUser().getId().intValue();
        List<ItemFundUse> itemFundUses = itemFundUseService.queryFundUseByItemIdAndUserId(Iid,UserTd);
        return ResponseEntity.ok(itemFundUses);
    }

    /**
     * 更改
     * @param itemFundUse
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateFund(@RequestBody ItemFundUse itemFundUse){
        itemFundUseService.updateFundUse(itemFundUse);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundUse(@PathVariable Integer id){
        itemFundUseService.deleteFundUse(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
