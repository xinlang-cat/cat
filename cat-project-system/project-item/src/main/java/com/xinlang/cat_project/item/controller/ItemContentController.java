package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.service.IItemContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 梁应昌
 * 2019/10/31
 */
@RestController
@RequestMapping("/item/content")
public class ItemContentController {

    @Autowired
    private IItemContentService planService;

    /**
     * 添加内容
     * @param itemContent
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> savePlan(@RequestBody ItemContent itemContent) {
        planService.savePlan(itemContent);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条内容
     * @param id 内容id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemContent> gatPlanById(@PathVariable Integer id){

        ItemContent itemContent = planService.queryPlanById(id);
        return ResponseEntity.ok(itemContent);
    }

    /**
     * 获取当前项目所有内容
     * @param Tid 项目id
     * @return
     */
    @GetMapping("/all/{Tid}")
    public ResponseEntity<List<ItemContent>> gatPlanByTid(@PathVariable Integer Tid){

        List<ItemContent> itemContent = planService.queryPlanByTId(Tid);
        return ResponseEntity.ok(itemContent);
    }

    /**
     * 更改
     * @param itemContent
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updatePlan(@RequestBody ItemContent itemContent){
        planService.updatePlan(itemContent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Integer id){
        planService.deletePlan(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
