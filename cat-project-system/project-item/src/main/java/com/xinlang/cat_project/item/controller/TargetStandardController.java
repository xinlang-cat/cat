package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.TargetStandard;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.cat_project.item.service.ITargetStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标准
 */
@RestController
@RequestMapping("/standard")
public class TargetStandardController {

    @Autowired
    private ITargetStandardService targetStandardService;

    @Autowired
    private IItemTargetService targetService;

    /**
     * 新增标准
     * @param targetStandard
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveTargetStandard(@RequestBody TargetStandard targetStandard) {
        targetStandardService.saveTargetStandard(targetStandard);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条标准
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<TargetStandard> gatTargetStandardById(@PathVariable Integer id){

        TargetStandard targetStandard = targetStandardService.queryTargetStandardById(id);
        return ResponseEntity.ok(targetStandard);
    }

    /**
     * 获取当前指标所有标准
     * @param Cid 指标id
     * @return
     */
    @GetMapping("/all/{Cid}")
    public ResponseEntity<List<TargetStandard>> gatTargetStandardByCid(@PathVariable Integer Cid){

        List<TargetStandard> targetStandard = targetStandardService.queryTargetStandardByTId(Cid);
        return ResponseEntity.ok(targetStandard);
    }

    /**
     * 更改
     * @param targetStandard
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateTargetStandard(@RequestBody TargetStandard targetStandard){

        targetStandardService.updateTargetStandard(targetStandard);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTargetStandard(@PathVariable Integer id){
        targetStandardService.deleteTargetStandard(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
