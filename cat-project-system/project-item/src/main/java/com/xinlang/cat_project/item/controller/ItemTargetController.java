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
     * 获取单条指标明细
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<TargetInfo> getTargetInfoById(@PathVariable Integer id){

        TargetInfo targetInfo = targetService.queryTargetInfoById(id);
        return ResponseEntity.ok(targetInfo);
    }

    /**
     * 获取当前内容全部指标
     * @param Cid 内容id
     * @return
     */
    @GetMapping("/all/{Cid}")
    public ResponseEntity<List<ItemTarget>> getTargetByCid(@PathVariable Integer Cid){

        List<ItemTarget> target = targetService.queryTargetByCId(Cid);
        return ResponseEntity.ok(target);
    }

    /**
     * 获取当前项目全部指标明细
     * @param Tid 项目id
     * @return
     */
    @GetMapping("/info/all/{Tid}")
    public ResponseEntity<List<TargetInfoAll>> getTargetInfoAllByCid(@PathVariable Integer Tid){

        List<TargetInfoAll> targetInfoAll = targetService.queryTargetInfoAllByCId(Tid);
        return ResponseEntity.ok(targetInfoAll);
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
