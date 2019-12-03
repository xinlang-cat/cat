package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.WorkSummary;
import com.xinlang.zly.summary.service.IWorkSummaryService;
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
@RequestMapping("/work")
public class WorkSummaryController {

    @Autowired
    private IWorkSummaryService workSummaryService;

    @PostMapping
    @LogAnnotation(module = "添加工作总结")
    @ApiOperation(value = "添加工作总结")
    public WorkSummary save(@RequestBody WorkSummary workSummary){
        Date date = new Date();
        workSummary.setCreateTime(date);
        workSummary.setUpdateTime(date);
        AppUser appUser = AppUserUtil.getLoginAppUser();
        workSummary.setCreateUserId(appUser.getId().intValue());
        workSummaryService.save(workSummary);
        return workSummary;
    }

    @PutMapping
    @LogAnnotation(module = "修改工作总结")
    @ApiOperation(value = "修改工作总结")
    public WorkSummary update(@RequestBody WorkSummary workSummary){
        workSummary.setUpdateTime(new Date());
        workSummaryService.update(workSummary);
        return workSummary;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询工作总结列表")
    @ApiOperation(value = "查询工作总结列表")
    public List<WorkSummary> findListByParams(@RequestParam Map<String,Object> params){
        return workSummaryService.findListByParams(params,WorkSummary.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询工作总结分页")
    @ApiOperation(value = "查询工作总结分页")
    public Page<WorkSummary> findPageByParams(@RequestParam Map<String,Object> params){
        return workSummaryService.findPageByParams(params,WorkSummary.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除工作总结")
    @ApiOperation(value = "删除工作总结")
    public void delete(@PathVariable Integer id){
        workSummaryService.delete(id);
    }
}
