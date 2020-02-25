package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.StageAffixFile;
import com.xinlang.zly.summary.service.IStageAffixFileService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/stageAffixFile")
public class StageAffixFileController {
    @Autowired
    private IStageAffixFileService stageAffixFileService;

    @PostMapping
    @LogAnnotation(module = "添加阶段总结的附件")
    @ApiOperation(value = "添加阶段总结的附件")
    public List<StageAffixFile> save(@RequestBody List<StageAffixFile> stageAffixFiles) {
        stageAffixFiles.forEach(stageAffixFile -> {
            stageAffixFileService.save(stageAffixFile);
        });
        return stageAffixFiles;
    }

    @PutMapping
    @LogAnnotation(module = "修改阶段总结的附件")
    @ApiOperation(value = "修改阶段总结的附件")
    public StageAffixFile update(@RequestBody StageAffixFile stageAffixFile) {
        stageAffixFileService.update(stageAffixFile);
        return stageAffixFile;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询阶段总结的附件列表")
    @ApiOperation(value = "查询阶段总结的附件列表")
    public List<StageAffixFile> findListByParams(@RequestParam Map<String, Object> params) {
        return stageAffixFileService.findListByParams(params, StageAffixFile.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除阶段总结的附件")
    @ApiOperation(value = "删除阶段总结的附件")
    public void delete(@PathVariable Integer id) { stageAffixFileService.delete(id);
    }

    @DeleteMapping("/{ids}")
    @LogAnnotation(module = "删除阶段总结的附件")
    @ApiOperation(value = "删除阶段总结的附件")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stageAffixFileService.delete(id);
        });
    }
}
