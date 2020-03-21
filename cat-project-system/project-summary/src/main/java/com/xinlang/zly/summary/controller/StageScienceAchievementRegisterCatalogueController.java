package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.StageScienceAchievementRegisterCatalogue;
import com.xinlang.zly.summary.service.IStageScienceAchievementRegisterCatalogueService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/stageScienceAchievementRegisterCatalogue")
public class StageScienceAchievementRegisterCatalogueController {
    @Autowired
    private IStageScienceAchievementRegisterCatalogueService stageScienceAchievementRegisterCatalogueService;

    @PostMapping
    @LogAnnotation(module = "科技成果登记目录")
    @ApiOperation(value = "科技成果登记目录")
    public List<StageScienceAchievementRegisterCatalogue> save(@RequestBody List<StageScienceAchievementRegisterCatalogue> stageScienceAchievementRegisterCatalogues) {
        stageScienceAchievementRegisterCatalogues.forEach(stageScienceAchievementRegisterCatalogue -> {
            stageScienceAchievementRegisterCatalogueService.save(stageScienceAchievementRegisterCatalogue);
        });
        return stageScienceAchievementRegisterCatalogues;
    }

    @PutMapping
    @LogAnnotation(module = "修改科技成果登记目录")
    @ApiOperation(value = "修改科技成果登记目录")
    public List<StageScienceAchievementRegisterCatalogue> update(@RequestBody List<StageScienceAchievementRegisterCatalogue> stageScienceAchievementRegisterCatalogues) {
        stageScienceAchievementRegisterCatalogues.forEach(stageScienceAchievementRegisterCatalogue -> {
            stageScienceAchievementRegisterCatalogueService.update(stageScienceAchievementRegisterCatalogue);
        });
        return stageScienceAchievementRegisterCatalogues;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询科技成果登记目录")
    @ApiOperation(value = "查询科技成果登记目录")
    public List<StageScienceAchievementRegisterCatalogue> findListByParams(@RequestParam Map<String, Object> params) {
        return stageScienceAchievementRegisterCatalogueService.findListByParams(params, StageScienceAchievementRegisterCatalogue.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除科技成果登记目录")
    @ApiOperation(value = "删除科技成果登记目录")
    public void delete(@PathVariable Integer id) { stageScienceAchievementRegisterCatalogueService.delete(id);
    }

    @DeleteMapping
    @LogAnnotation(module = "删除科技成果登记目录")
    @ApiOperation(value = "删除科技成果登记目录")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stageScienceAchievementRegisterCatalogueService.delete(id);
        });
    }
}
