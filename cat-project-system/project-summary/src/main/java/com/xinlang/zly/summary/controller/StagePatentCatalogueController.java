package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.StagePatentCatalogue;
import com.xinlang.zly.summary.service.IStagePatentCatalogueService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/stagePatentCatalogue")
public class StagePatentCatalogueController {
    @Autowired
    private IStagePatentCatalogueService stagePatentCatalogueService;

    @PostMapping
    @LogAnnotation(module = "添加授权专利目录")
    @ApiOperation(value = "添加授权专利目录")
    public List<StagePatentCatalogue> save(@RequestBody List<StagePatentCatalogue> stagePatentCatalogues) {
        stagePatentCatalogues.forEach(stagePatentCatalogue -> {
            stagePatentCatalogueService.save(stagePatentCatalogue);
        });
        return stagePatentCatalogues;
    }

    @PutMapping
    @LogAnnotation(module = "修改授权专利目录")
    @ApiOperation(value = "修改授权专利目录")
    public List<StagePatentCatalogue> update(@RequestBody List<StagePatentCatalogue> stagePatentCatalogues) {
        stagePatentCatalogues.forEach(stagePatentCatalogue -> {
            stagePatentCatalogueService.update(stagePatentCatalogue);
        });
        return stagePatentCatalogues;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询授权专利目录列表")
    @ApiOperation(value = "查询授权专利目录列表")
    public List<StagePatentCatalogue> findListByParams(@RequestParam Map<String, Object> params) {
        return stagePatentCatalogueService.findListByParams(params, StagePatentCatalogue.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除授权专利目录")
    @ApiOperation(value = "删除授权专利目录")
    public void delete(@PathVariable Integer id) { stagePatentCatalogueService.delete(id);
    }

    @DeleteMapping("/{ids}")
    @LogAnnotation(module = "删除授权专利目录")
    @ApiOperation(value = "删除授权专利目录")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stagePatentCatalogueService.delete(id);
        });
    }
}
