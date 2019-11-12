package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemBudget;
import com.xinlang.cat_project.item.service.IItemBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/budget")
public class ItemBudgetController {

    @Autowired
    private IItemBudgetService itemBudgetService;

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Integer id){
        itemBudgetService.deleteBudget(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
