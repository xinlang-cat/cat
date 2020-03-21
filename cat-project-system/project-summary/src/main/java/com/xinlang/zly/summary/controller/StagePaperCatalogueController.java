package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.StagePaperCatalogue;
import com.xinlang.zly.summary.service.IStagePaperCatalogueService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/stagePaperCatalogue")
public class StagePaperCatalogueController {

    @Autowired
    private IStagePaperCatalogueService stagePaperCatalogueService;

    @PostMapping
    @LogAnnotation(module = "添加专著、论文目录")
    @ApiOperation(value = "添加专著、论文目录")
    public List<StagePaperCatalogue> save(@RequestBody List<StagePaperCatalogue> stagePaperCatalogues) {
        stagePaperCatalogues.forEach(stagePaperCatalogue -> {
            stagePaperCatalogueService.save(stagePaperCatalogue);
        });
        return stagePaperCatalogues;
    }

    @PutMapping
    @LogAnnotation(module = "修改专著、论文目录")
    @ApiOperation(value = "修改专著、论文目录")
    public List<StagePaperCatalogue> update(@RequestBody List<StagePaperCatalogue> stagePaperCatalogues) {
        stagePaperCatalogues.forEach(stagePaperCatalogue -> {
            stagePaperCatalogueService.update(stagePaperCatalogue);
        });
        return stagePaperCatalogues;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询专著、论文目录列表")
    @ApiOperation(value = "查询专著、论文目录列表")
    public List<StagePaperCatalogue> findListByParams(@RequestParam Map<String, Object> params) {
        return stagePaperCatalogueService.findListByParams(params, StagePaperCatalogue.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除专著、论文目录")
    @ApiOperation(value = "删除专著、论文目录")
    public void delete(@PathVariable Integer id) { stagePaperCatalogueService.delete(id);
    }

    @DeleteMapping
    @LogAnnotation(module = "删除专著、论文目录")
    @ApiOperation(value = "删除专著、论文目录")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stagePaperCatalogueService.delete(id);
        });
    }
}
