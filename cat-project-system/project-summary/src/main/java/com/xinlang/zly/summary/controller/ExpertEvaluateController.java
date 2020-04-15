package com.xinlang.zly.summary.controller;

import com.xinlang.bean.project_user.ProjectUserItem;
import com.xinlang.zly.summary.bean.CheckTable;
import com.xinlang.zly.summary.bean.ExpertEvaluate;
import com.xinlang.zly.summary.bean.ExpertEvaluateAffiliate;
import com.xinlang.zly.summary.fegin.ConsumeItemUser;
import com.xinlang.zly.summary.service.ICheckTableService;
import com.xinlang.zly.summary.service.IExpertEvaluateAffiliateService;
import com.xinlang.zly.summary.service.IExpertEvaluateService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.*;

@RestController
@RequestMapping("/expert-evaluate")
public class ExpertEvaluateController {
    @Autowired
    private IExpertEvaluateService expertEvaluateService;
    @Autowired
    private ConsumeItemUser consumeItemUser;
    @Autowired
    private ICheckTableService checkTableService;
    @Autowired
    private IExpertEvaluateAffiliateService expertEvaluateAffiliateService;

    @PostMapping
    @LogAnnotation(module = "添加专家评估报告")
    @ApiOperation(value = "添加专家评估报告")
    public ExpertEvaluate save(@RequestBody ExpertEvaluate expertEvaluate) {

        Date date = new Date();
        expertEvaluate.setCreateTime(date);
        expertEvaluateService.save(expertEvaluate);
        Map<String,Object> map = new HashMap<>();
        map.put("itemId",expertEvaluate.getItemId());
        List<ExpertEvaluate> expertEvaluates = expertEvaluateService.findListByParams(map,ExpertEvaluate.class);
        List<ProjectUserItem> projectUserItems = consumeItemUser.findExpertByItemId(expertEvaluate.getItemId());
        expertEvaluate.getList().forEach(item->{
            item.setCreateTime(date);
            item.setUserId(expertEvaluate.getUserId());
            item.setUserName(expertEvaluate.getUserName());
            item.setItemId(expertEvaluate.getItemId());
            item.setExpertEvaluateId(expertEvaluate.getId());
            expertEvaluateAffiliateService.save(item);
        });
        if(expertEvaluates.size() == projectUserItems.size()){
            List<CheckTable> checkTables = checkTableService.findListByParams(map,CheckTable.class);
            CheckTable checkTable = new CheckTable();
            checkTable.setId(checkTables.get(0).getId());
            checkTable.setStatus(4);
            checkTableService.update(checkTable);
        }
        return expertEvaluate;
    }

    @PutMapping
    @LogAnnotation(module = "修改专家评估报告")
    @ApiOperation(value = "修改专家评估报告")
    public ExpertEvaluate update(@RequestBody ExpertEvaluate expertEvaluate) {
        Date date = new Date();
        expertEvaluate.setUpdateTime(date);
        expertEvaluateService.update(expertEvaluate);
        expertEvaluate.getList().forEach(item->{
            item.setCreateTime(date);
            expertEvaluateAffiliateService.update(item);
        });
        return expertEvaluate;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询专家评估报告列表")
    @ApiOperation(value = "查询专家评估报告列表")
    public List<ExpertEvaluate> findListByParams(@RequestParam Map<String, Object> params) {
        List<ExpertEvaluate> result = expertEvaluateService.findListByParams(params, ExpertEvaluate.class);
        Map<String,Object> map = new HashMap<>();
        result.forEach(item->{
            map.put("expertEvaluateId",item.getId());
            List<ExpertEvaluateAffiliate> list = expertEvaluateAffiliateService.findListByParams(map,ExpertEvaluateAffiliate.class);
            item.setList(list);
        });
        return result;
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除专家评估报告")
    @ApiOperation(value = "删除专家评估报告")
    public void delete(@PathVariable Integer id) {
        expertEvaluateService.delete(id);
    }

    @DeleteMapping
    @LogAnnotation(module = "删除专家评估报告")
    @ApiOperation(value = "删除专家评估报告")
    public void deleteByIds(@PathVariable Set<Integer> ids) {
        ids.forEach(id -> {
            expertEvaluateService.delete(id);
        });
    }
}
