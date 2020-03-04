package com.xinlang.zly.summary.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.zly.summary.bean.*;
import com.xinlang.zly.summary.fegin.ConsumeProjectUser;
import com.xinlang.zly.summary.service.*;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-01-31
 */
@RestController
@RequestMapping("/stage")
public class StageController {
    @Autowired
    private IStageService stageService;
    @Autowired
    private ConsumeProjectUser consumeProjectUser;
    @Autowired
    private IStageFundUseService stageFundUseService;
    @Autowired
    private IStageTargetService stageTargetService;
    @Autowired
    private IStageAffixFileService stageAffixFileService;
    @Autowired
    private IStagePaperCatalogueService stagePaperCatalogueService;
    @Autowired
    private IStagePatentCatalogueService stagePatentCatalogueService;
    @Autowired
    private IStageScienceAchievementAwardCatalogueService stageScienceAchievementAwardCatalogueService;
    @Autowired
    private IStageScienceAchievementRegisterCatalogueService stageScienceAchievementRegisterCatalogueService;
    @Autowired
    private IStageTechnologyPactRegisterCatalogueService stageTechnologyPactRegisterCatalogueService;

    @InitBinder
    public void InitBinder (ServletRequestDataBinder binder){
        binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @PostMapping
    @LogAnnotation(module = "添加阶段总结")
    @ApiOperation(value = "添加阶段总结")
    public Stage save(@RequestBody Stage stage) {
        AppUser appUser = AppUserUtil.getLoginAppUser();
        ProjectUser projectUser = consumeProjectUser.findByUserId(appUser.getId().intValue()).get(0);
        stage.setCreateUserName(projectUser.getName());
        stage.setCreateUserId(appUser.getId().intValue());
        stage.setCreateTime(new Date());
        //保存基础信息
        stageService.save(stage);
        Integer stageId = stage.getId();
        stage.getStageFundUses().forEach(stageFundUse->{
            stageFundUse.setStageId(stageId);
            stageFundUseService.save(stageFundUse);
        });
        stage.getStageAffixFiles().forEach(stageAffixFile -> {
            stageAffixFile.setStageId(stageId);
            stageAffixFileService.save(stageAffixFile);
        });
        stage.getStagePaperCatalogues().forEach(stagePaperCatalogue -> {
            stagePaperCatalogue.setStageId(stageId);
            stagePaperCatalogueService.save(stagePaperCatalogue);
        });
        stage.getStagePatentCatalogues().forEach(stagePatentCatalogue -> {
            stagePatentCatalogue.setStageId(stageId);
            stagePatentCatalogueService.save(stagePatentCatalogue);
        });
        stage.getStageScienceAchievementAwardCatalogues().forEach(stageScienceAchievementAwardCatalogue -> {
            stageScienceAchievementAwardCatalogue.setStageId(stageId);
            stageScienceAchievementAwardCatalogueService.save(stageScienceAchievementAwardCatalogue);
        });
        stage.getStageScienceAchievementRegisterCatalogues().forEach(stageScienceAchievementRegisterCatalogue -> {
            stageScienceAchievementRegisterCatalogue.setStageId(stageId);
            stageScienceAchievementRegisterCatalogueService.save(stageScienceAchievementRegisterCatalogue);
        });
        stage.getStageTechnologyPactRegisterCatalogues().forEach(stageTechnologyPactRegisterCatalogue -> {
            stageTechnologyPactRegisterCatalogue.setStageId(stageId);
            stageTechnologyPactRegisterCatalogueService.save(stageTechnologyPactRegisterCatalogue);
        });
        stage.getStageTargets().forEach(stageTarget -> {
            stageTarget.setStageId(stageId);
            stageTargetService.save(stageTarget);
        });
        return stage;
    }

    @PutMapping
    @LogAnnotation(module = "修改阶段总结")
    @ApiOperation(value = "修改阶段总结")
    public Stage update(@RequestBody Stage stage) {
        stageService.update(stage);
        return stage;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询阶段总结列表")
    @ApiOperation(value = "查询阶段总结列表")
    public List<Stage> findListByParams(@RequestParam Map<String, Object> params) {
        return stageService.findListByParams(params, Stage.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询阶段总结分页")
    @ApiOperation(value = "查询阶段总结分页")
    public Page<Stage> findPageByParams(@RequestParam Map<String, Object> params) {
        return stageService.findPageByParams(params, Stage.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除阶段总结")
    @ApiOperation(value = "删除阶段总结")
    public void delete(@PathVariable Integer id) { stageService.delete(id);
    }

    @DeleteMapping("/{ids}")
    @LogAnnotation(module = "删除阶段总结")
    @ApiOperation(value = "删除阶段总结")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id->{
            stageService.delete(id);
        });
    }
}
