package com.xinlang.zly.summary.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.zly.summary.bean.SkillSummary;
import com.xinlang.zly.summary.fegin.ConsumeProjectUser;
import com.xinlang.zly.summary.service.ISkillSummaryService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-03
 */
@RestController
@RequestMapping("/skill")
public class SkillSummaryController {

    @Autowired
    private ISkillSummaryService skillSummaryService;
    @Autowired
    private ConsumeProjectUser consumeProjectUser;

    @PostMapping
    @LogAnnotation(module = "添加技术总结")
    @ApiOperation(value = "添加技术总结")
    public SkillSummary save(@RequestBody SkillSummary skillSummary){
        Date date = new Date();
        skillSummary.setCreateTime(date);
        skillSummary.setUpdateTime(date);
        AppUser appUser = AppUserUtil.getLoginAppUser();
        Integer userId = appUser.getId().intValue();
        ProjectUser projectUser = consumeProjectUser.findByUserId(userId).get(0);
        skillSummary.setCreateUserId(userId);
        skillSummary.setCreateUserName(projectUser.getName());
        skillSummaryService.save(skillSummary);
        return skillSummary;
    }

    @PutMapping
    @LogAnnotation(module = "修改技术总结")
    @ApiOperation(value = "修改技术总结")
    public SkillSummary update(@RequestBody SkillSummary skillSummary){
        skillSummary.setUpdateTime(new Date());
        skillSummaryService.update(skillSummary);
        return skillSummary;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询技术总结列表")
    @ApiOperation(value = "查询技术总结列表")
    public List<SkillSummary> findListByParams(@RequestParam Map<String,Object> params){
        return skillSummaryService.findListByParams(params,SkillSummary.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询技术总结分页")
    @ApiOperation(value = "查询技术总结分页")
    public Page<SkillSummary> findPageByParams(@RequestParam Map<String,Object> params){
        return skillSummaryService.findPageByParams(params,SkillSummary.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除技术总结")
    @ApiOperation(value = "删除技术总结")
    public void delete(@PathVariable Integer id){
        skillSummaryService.delete(id);
    }

}
