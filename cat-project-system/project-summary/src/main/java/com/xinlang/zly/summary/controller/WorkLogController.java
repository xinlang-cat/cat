package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.WorkLog;
import com.xinlang.zly.summary.fegin.ConsumeProjectUser;
import com.xinlang.zly.summary.service.IWorkLogService;
import com.xinlang.zly.summary.service.IWorkLogService;
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
 * 2019-12-20
 */
@RestController
@RequestMapping("/work-log")
public class WorkLogController {

    @Autowired
    private IWorkLogService workLogService;
    @Autowired
    private ConsumeProjectUser consumeProjectUser;

    @PostMapping
    @LogAnnotation(module = "添加工作日志")
    @ApiOperation(value = "添加工作日志")
    public WorkLog save(@RequestBody WorkLog workLog){
        workLog.setCreateTime(new Date());
        AppUser appUser = AppUserUtil.getLoginAppUser();
        workLog.setCreateUserId(appUser.getId().intValue());
        workLog.setCreateUserName(consumeProjectUser.findByUserId(appUser.getId().intValue()).get(0).getName());
        workLogService.save(workLog);
        return workLog;
    }

    @PutMapping
    @LogAnnotation(module = "修改工作日志")
    @ApiOperation(value = "修改工作日志")
    public WorkLog update(@RequestBody WorkLog workLog){
        workLogService.update(workLog);
        return workLog;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询工作日志列表")
    @ApiOperation(value = "查询工作日志列表")
    public List<WorkLog> findListByParams(@RequestParam Map<String,Object> params){
        return workLogService.findListByParams(params,WorkLog.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询工作日志分页")
    @ApiOperation(value = "查询工作日志分页")
    public Page<WorkLog> findPageByParams(@RequestParam Map<String,Object> params){
        return workLogService.findPageByParams(params,WorkLog.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除工作日志")
    @ApiOperation(value = "删除工作日志")
    public void delete(@PathVariable Integer id){
        workLogService.delete(id);
    }

    @GetMapping("/lately")
    @ApiOperation(value = "查询最新一条工作日志")
    @LogAnnotation(module = "查询最新一条工作日志")
    public WorkLog findLatelyByItemIdAndTargetId(@RequestParam Integer itemId,@RequestParam Integer targetId){
        return workLogService.findLatelyByItemIdAndTargetId(itemId,targetId);
    }
}
