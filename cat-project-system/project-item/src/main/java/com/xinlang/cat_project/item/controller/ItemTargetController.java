package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.VO.TargetInfo;
import com.xinlang.cat_project.item.VO.TargetInfoAll;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.service.IItemTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 指标
 */
@RestController
@RequestMapping("/item/target")
public class ItemTargetController {

    @Autowired
    private IItemTargetService targetService;

    /**
     * 新增指标
     * @param target
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveTarget(@RequestBody ItemTarget target){
        targetService.saveTarget(target);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条指标
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemTarget> getTargetById(@PathVariable Integer id){

        ItemTarget target = targetService.queryTargetById(id);
        return ResponseEntity.ok(target);
    }

    /**
     * 获取当前内容全部指标以及实施人员
     * @param Cid 内容id
     * @return
     */
    @GetMapping("/all/{Cid}")
    public ResponseEntity<List<Map<String, Object>>> getTargetByCid(@PathVariable Integer Cid){

        List<Map<String, Object>> target = targetService.queryTargetByCId(Cid);
        return ResponseEntity.ok(target);
    }

    /**
     * 更改
     * @param target
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateTarget(@RequestBody ItemTarget target){
        targetService.updateTarget(target);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable Integer id){
        targetService.deleteTarget(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
