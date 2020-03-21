package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.StageTechnologyPactRegisterCatalogue;
import com.xinlang.zly.summary.service.IStageTechnologyPactRegisterCatalogueService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/stageTechnologyPactRegisterCatalogue")
public class StageTechnologyPactRegisterCatalogueController {
    @Autowired
    private IStageTechnologyPactRegisterCatalogueService stageTechnologyPactRegisterCatalogueService;

    @PostMapping
    @LogAnnotation(module = "添加技术合同认定登记目录")
    @ApiOperation(value = "添加技术合同认定登记目录")
    public List<StageTechnologyPactRegisterCatalogue> save(@RequestBody List<StageTechnologyPactRegisterCatalogue> stageTechnologyPactRegisterCatalogues) {
        stageTechnologyPactRegisterCatalogues.forEach(stageTechnologyPactRegisterCatalogue -> {
            stageTechnologyPactRegisterCatalogueService.save(stageTechnologyPactRegisterCatalogue);
        });
        return stageTechnologyPactRegisterCatalogues;
    }

    @PutMapping
    @LogAnnotation(module = "修改技术合同认定登记目录")
    @ApiOperation(value = "修改技术合同认定登记目录")
    public List<StageTechnologyPactRegisterCatalogue> update(@RequestBody List<StageTechnologyPactRegisterCatalogue> stageTechnologyPactRegisterCatalogues) {
        stageTechnologyPactRegisterCatalogues.forEach(stageTechnologyPactRegisterCatalogue -> {
            stageTechnologyPactRegisterCatalogueService.update(stageTechnologyPactRegisterCatalogue);
        });
        return stageTechnologyPactRegisterCatalogues;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询技术合同认定登记目录")
    @ApiOperation(value = "查询技术合同认定登记目录")
    public List<StageTechnologyPactRegisterCatalogue> findListByParams(@RequestParam Map<String, Object> params) {
        return stageTechnologyPactRegisterCatalogueService.findListByParams(params, StageTechnologyPactRegisterCatalogue.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除技术合同认定登记目录")
    @ApiOperation(value = "删除技术合同认定登记目录")
    public void delete(@PathVariable Integer id) { stageTechnologyPactRegisterCatalogueService.delete(id);
    }

    @DeleteMapping
    @LogAnnotation(module = "删除技术合同认定登记目录")
    @ApiOperation(value = "删除技术合同认定登记目录")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stageTechnologyPactRegisterCatalogueService.delete(id);
        });
    }
}
