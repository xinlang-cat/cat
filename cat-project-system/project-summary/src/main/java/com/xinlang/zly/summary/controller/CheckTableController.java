package com.xinlang.zly.summary.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.zly.summary.bean.CheckTable;
import com.xinlang.zly.summary.bean.SkillSummary;
import com.xinlang.zly.summary.fegin.ConsumeProjectUser;
import com.xinlang.zly.summary.service.ICheckTableService;
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
@RequestMapping("/check-table")
public class CheckTableController {

    @Autowired
    private ICheckTableService checkTableService;

    @PostMapping
    @LogAnnotation(module = "添加结题申请表")
    @ApiOperation(value = "添加结题申请表")
    public CheckTable save(@RequestBody CheckTable checkTable){
        checkTable.setApplicationDate(new Date());
        checkTableService.save(checkTable);
        return checkTable;
    }

    @PutMapping
    @LogAnnotation(module = "修改结题申请表")
    @ApiOperation(value = "修改结题申请表")
    public CheckTable update(@RequestBody CheckTable checkTable){
        checkTableService.update(checkTable);
        return checkTable;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询结题申请表列表")
    @ApiOperation(value = "查询结题申请表列表")
    public List<CheckTable> findListByParams(@RequestParam Map<String,Object> params){
        return checkTableService.findListByParams(params,CheckTable.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询结题申请表分页")
    @ApiOperation(value = "查询结题申请表分页")
    public Page<CheckTable> findPageByParams(@RequestParam Map<String,Object> params){
        return checkTableService.findPageByParams(params,CheckTable.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除结题申请表")
    @ApiOperation(value = "删除结题申请表")
    public void delete(@PathVariable Integer id){
        checkTableService.delete(id);
    }

}
