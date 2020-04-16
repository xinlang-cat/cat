package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.*;
import com.xinlang.cat_project.item.service.IAuditApplyService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 指标
 */
@RestController
@RequestMapping("/item/target")
public class ItemTargetController {


    @Autowired
    private IAuditApplyService auditApplyService;


    @ApiOperation(value = "添加指标查定")
    @LogAnnotation(module = "添加指标查定")
    @PostMapping("/auditApply/one")
    public ResponseEntity<Void> saveAuditApply(@RequestBody auditApply auditApply) throws ParseException {
        System.err.println(auditApply);
        if ( null!=auditApply.getPeriod()&&!auditApply.getPeriod().isEmpty() && !auditApply.getPeriod().equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = simpleDateFormat.parse(auditApply.getPeriod().substring(0, 10));
            auditApply.setStart_date(date);
            date = simpleDateFormat.parse(auditApply.getPeriod().substring(12));
            auditApply.setEnd_date(date);
        }

        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        auditApply.setEdit_userid(userId);
        auditApply.setEdit_date(new Date());
        auditApply.setStatus(1);
        auditApplyService.save(auditApply);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询指标查定")
    @LogAnnotation(module = "查询指标查定")
    @Transactional
    @GetMapping("/applyList")
    public ResponseEntity<List<auditApply>> getApplyById(@RequestParam Map<String, Object> params){
        List<auditApply> targets = auditApplyService.findListByParams(params,auditApply.class);

        return ResponseEntity.ok(targets);
    }

    @ApiOperation(value = "修改指标查定")
    @LogAnnotation(module = "修改指标查定")
    @PutMapping("/auditApply/update")
    public ResponseEntity<Void> updateAuditApply(@RequestBody auditApply auditApply) throws ParseException {
        if (auditApply.getPeriods()!= null && !auditApply.getPeriods().equals("")){
            if (!auditApply.getPeriods().equals("")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                date = simpleDateFormat.parse(auditApply.getPeriods().substring(0, 10));
                auditApply.setStart_date_true(date);
                date = simpleDateFormat.parse(auditApply.getPeriods().substring(12));
                auditApply.setEnd_date_true(date);
            }
        }


        Integer userId =AppUserUtil.getLoginAppUser().getId().intValue();
        auditApply.setCheck_userid(userId);
        auditApplyService.update(auditApply);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询指标查定")
    @LogAnnotation(module = "查询指标查定")
    @Transactional
    @GetMapping("/applyList/list")
    public ResponseEntity<List<auditApply>> getApplyCheck(@RequestParam Map<String, Object> params){
        List<auditApply> targets = auditApplyService.findApplyList(params,auditApply.class);
        System.out.println(targets);
        return ResponseEntity.ok(targets);
    }

    @LogAnnotation(module = "获取变更申请列表")
    @GetMapping("/applyList/page")
    public ResponseEntity<PageResult<auditApply>> getModifyApplyAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                                     @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                     @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                     @RequestParam(required = false) Map<String, Object> params){


        PageResult<auditApply> result = auditApplyService.queryList(page,rows,sortBy,desc,params);
        return ResponseEntity.ok(result);





    }

}
