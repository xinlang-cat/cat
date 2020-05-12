package com.xinlang.zly.label.controller;

import com.xinlang.zly.label.bean.PolicyFile;
import com.xinlang.zly.label.service.IPolicyFileService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/policy-file")
public class PolicyFileController {

    @Autowired
    private IPolicyFileService policyFileService;

    @PostMapping
    @LogAnnotation(module = "添加政策文件")
    @ApiOperation(value = "添加政策文件")
    public PolicyFile save(@RequestBody PolicyFile policyFile) {
        policyFile.setUpDate(new Date());
        policyFileService.save(policyFile);
        return policyFile;
    }


    @PutMapping
    @LogAnnotation(module = "修改政策文件")
    @ApiOperation(value = "修改政策文件")
    public PolicyFile update(@RequestBody PolicyFile policyFile) {
        policyFileService.update(policyFile);
        return policyFile;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询政策文件列表")
    @ApiOperation(value = "查询政策文件列表")
    public List<PolicyFile> findListByParams(@RequestParam Map<String, Object> params) {
        return policyFileService.findListByParams(params, PolicyFile.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询政策文件列表")
    @ApiOperation(value = "查询政策文件列表")
    public Page<PolicyFile> findPageByParams(@RequestParam Map<String, Object> params) {
        return policyFileService.findPageByParams(params, PolicyFile.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除政策文件")
    @ApiOperation(value = "删除政策文件")
    public void delete(@PathVariable Integer id) {
        policyFileService.delete(id);
    }

    @DeleteMapping
    @LogAnnotation(module = "删除政策文件")
    @ApiOperation(value = "删除政策文件")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id -> {
            policyFileService.delete(id);
        });
    }
}
