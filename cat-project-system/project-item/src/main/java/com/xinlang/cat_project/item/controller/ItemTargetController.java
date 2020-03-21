package com.xinlang.cat_project.item.controller;

import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.pojo.*;
import com.xinlang.cat_project.item.service.IAuditApplyService;
import com.xinlang.cat_project.item.service.IItemBasicService;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    private IItemTargetService targetService;
    @Autowired
    private IAuditApplyService auditApplyService;
    @Autowired
    private IItemBasicService itemBasicService;

    @ApiOperation(value = "添加一条指标")
    @LogAnnotation(module = "添一条加指标")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/one")
    public ResponseEntity<Void> saveTarget(@RequestBody ItemTarget target){
        targetService.save(target);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加多条指标")
    @LogAnnotation(module = "添加多条指标")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<List<ItemTarget>> saveTargets(@RequestBody List<ItemTarget> itemTargets) {
        targetService.saveTargets(itemTargets);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemTargets);
    }

    @ApiOperation(value = "查询指标")
    @LogAnnotation(module = "查询指标")
    @Transactional
    @GetMapping("/list")
    public ResponseEntity<List<ItemTarget>> getTargetById(@RequestParam Map<String, Object> params){
        List<ItemTarget> targets = targetService.findListByParams(params,ItemTarget.class);
        for (ItemTarget target : targets) {
            List<Integer> ids = targetService.findTargetUsers(target.getId(),target.getItem_id());
            String str = "";
            for (Integer id : ids) {
                if(str.equals("")){
                    str = id.toString();
                }else {
                    str += ","+ id.toString();
                }
            }
            target.setUserIds(str);
        }
        return ResponseEntity.ok(targets);
    }

    @ApiOperation(value = "修改指标")
    @LogAnnotation(module = "修改指标")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping("/one")
    public ResponseEntity<Void> updateTarget(@RequestBody ItemTarget target){
        targetService.update(target);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "修改多条指标")
    @LogAnnotation(module = "修改多条指标")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping("/multi")
    public ResponseEntity<Void> updateTargets(@RequestBody List<ItemTarget> itemTargets){
        //先删除所有指标
        targetService.deleteTargetByItemId(itemTargets.get(0).getItem_id());
        //重新添加
        targetService.saveTargets(itemTargets);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除指标")
    @LogAnnotation(module = "删除指标")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable Integer id){
        targetService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加指标查定")
    @LogAnnotation(module = "添加指标查定")
    @PostMapping("/auditApply/one")
    public ResponseEntity<Void> saveAuditApply(@RequestBody auditApply auditApply){
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
    public ResponseEntity<Void> updateAuditApply(@RequestBody auditApply auditApply){
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

    @LogAnnotation(module = "获取更改申请列表")
    @GetMapping("/applyList/page")
    public ResponseEntity<PageResult<auditApply>> getModifyApplyAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                                     @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                     @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                     @RequestParam(required = false) Map<String, Object> params){
        String count = (String) params.get("count");
        String name = (String) params.get("item_name");
        Boolean search =true;
        if (count == "" || count == null ){

        }else {
            Integer weth = Integer.parseInt(count);
            List<ItemTarget> targets = targetService.findQuantity(weth);
            List<Integer> ids =new ArrayList<>();
            if (targets.size()==0){
                ids.add(0);
                search=false;
            }else {
                for (int i=0;i<targets.size();i++){
                    ids.add(targets.get(i).getId());
                }
                params.put("ids",ids);
            }
        }


        if (name == "" || name == null ){

        }else {
            List<ItemBasic> itemBasics = itemBasicService.findByName(name);
            List<Integer> itemIds =new ArrayList<>();
            if (itemIds.size()==0){
                itemIds.add(0);
            }else {
                for (int i=0;i<itemBasics.size();i++){
                    itemIds.add(itemBasics.get(i).getId());
                }
            }
            params.put("itemIds",itemIds);
        }
        System.err.println("查询");
        PageResult<auditApply> result = auditApplyService.queryList(page,rows,sortBy,desc,params);
        return ResponseEntity.ok(result);





    }

}
