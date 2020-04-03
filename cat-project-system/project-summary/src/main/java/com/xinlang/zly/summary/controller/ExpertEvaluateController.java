package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.ExpertEvaluate;
import com.xinlang.zly.summary.service.IExpertEvaluateService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/expert-evaluate")
public class ExpertEvaluateController {
    @Autowired
    private IExpertEvaluateService expertEvaluateService;

    @PostMapping
    @LogAnnotation(module = "添加专家评估报告")
    @ApiOperation(value = "添加专家评估报告")
    public ExpertEvaluate save(@RequestBody ExpertEvaluate expertEvaluate) {
        expertEvaluate.setCreateTime(new Date());
        expertEvaluateService.save(expertEvaluate);
        return expertEvaluate;
    }

    @PutMapping
    @LogAnnotation(module = "修改专家评估报告")
    @ApiOperation(value = "修改专家评估报告")
    public ExpertEvaluate update(@RequestBody ExpertEvaluate expertEvaluate) {
        expertEvaluate.setUpdateTime(new Date());
        expertEvaluateService.update(expertEvaluate);
        return expertEvaluate;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询专家评估报告列表")
    @ApiOperation(value = "查询专家评估报告列表")
    public List<ExpertEvaluate> findListByParams(@RequestParam Map<String, Object> params) {
        return expertEvaluateService.findListByParams(params, ExpertEvaluate.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除专家评估报告")
    @ApiOperation(value = "删除专家评估报告")
    public void delete(@PathVariable Integer id) {
        expertEvaluateService.delete(id);
    }

    @DeleteMapping
    @LogAnnotation(module = "删除专家评估报告")
    @ApiOperation(value = "删除专家评估报告")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id -> {
            expertEvaluateService.delete(id);
        });
    }
}
