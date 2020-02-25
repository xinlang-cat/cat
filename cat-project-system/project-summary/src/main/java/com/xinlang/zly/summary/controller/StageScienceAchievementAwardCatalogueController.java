package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.StageScienceAchievementAwardCatalogue;
import com.xinlang.zly.summary.service.IStageScienceAchievementAwardCatalogueService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/stageScienceAchievementAwardCatalogue")
public class StageScienceAchievementAwardCatalogueController {
    @Autowired
    private IStageScienceAchievementAwardCatalogueService stageScienceAchievementAwardCatalogueService;

    @PostMapping
    @LogAnnotation(module = "科技成果奖励目录")
    @ApiOperation(value = "科技成果奖励目录")
    public List<StageScienceAchievementAwardCatalogue> save(@RequestBody List<StageScienceAchievementAwardCatalogue> stageScienceAchievementAwardCatalogues) {
        stageScienceAchievementAwardCatalogues.forEach(stageScienceAchievementAwardCatalogue -> {
            stageScienceAchievementAwardCatalogueService.save(stageScienceAchievementAwardCatalogue);
        });
        return stageScienceAchievementAwardCatalogues;
    }

    @PutMapping
    @LogAnnotation(module = "修改授权专利目录")
    @ApiOperation(value = "修改授权专利目录")
    public List<StageScienceAchievementAwardCatalogue> update(@RequestBody List<StageScienceAchievementAwardCatalogue> stageScienceAchievementAwardCatalogues) {
        stageScienceAchievementAwardCatalogues.forEach(stageScienceAchievementAwardCatalogue -> {
            stageScienceAchievementAwardCatalogueService.update(stageScienceAchievementAwardCatalogue);
        });
        return stageScienceAchievementAwardCatalogues;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询授权专利目录列表")
    @ApiOperation(value = "查询授权专利目录列表")
    public List<StageScienceAchievementAwardCatalogue> findListByParams(@RequestParam Map<String, Object> params) {
        return stageScienceAchievementAwardCatalogueService.findListByParams(params, StageScienceAchievementAwardCatalogue.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除授权专利目录")
    @ApiOperation(value = "删除授权专利目录")
    public void delete(@PathVariable Integer id) { stageScienceAchievementAwardCatalogueService.delete(id);
    }

    @DeleteMapping("/{ids}")
    @LogAnnotation(module = "删除授权专利目录")
    @ApiOperation(value = "删除授权专利目录")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stageScienceAchievementAwardCatalogueService.delete(id);
        });
    }
}
