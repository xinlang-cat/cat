package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.cat_project.item.service.IAuditApplyService;
import com.xinlang.cat_project.item.service.IItemBasicService;
import com.xinlang.cat_project.item.service.IModifyApplyService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 梁应昌
 * 2019/9/27
 */

@RestController
@RequestMapping("/item/basic")
public class ItemBasicController {

    @Autowired
    private IItemBasicService itemBasicService;
    @Autowired
    private IModifyApplyService modifyApplyService;

    /**
     * 查询项目，分页查询
     * @param page 当前页
     * @param rows 每页大小
     * @param sortBy 排序字段 (预留，暂时没用到)
     * @param desc 是否为降序 (预留，暂时没用到)
     * @param params 参数
     * @return
     */
    @LogAnnotation(module = "获取项目列表")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ItemBasic>> getItemAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                            @RequestParam(required = false) Map<String, Object> params){

        PageResult<ItemBasic> result = itemBasicService.queryList(page,rows,sortBy,desc,params);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "添加项目信息")
    @LogAnnotation(module = "添加项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemBasic> saveItem(@RequestBody ItemBasic itemBasic) {
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemBasic.setEdit_userid(userId);
        itemBasic.setEdit_date(new Date());
        itemBasicService.save(itemBasic);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemBasic);
    }
    @ApiOperation(value = "查询项目信息")
    @LogAnnotation(module = "查询项目信息")
    @GetMapping("list")
    public ResponseEntity<List<ItemBasic>> getItemById(@RequestParam Map<String, Object> params){
        List<ItemBasic> basic = itemBasicService.findListByParams(params,ItemBasic.class);
        return ResponseEntity.ok(basic);
    }

    @ApiOperation(value = "查询当前用户相关的项目")
    @LogAnnotation(module = "查询当前用户相关的项目")
    @GetMapping("/company")
    public ResponseEntity<List<ItemBasic>> getCompanyItem(){
        List<ItemBasic> basic = itemBasicService.queryCompanyItem();
        return ResponseEntity.ok(basic);
    }

    @ApiOperation(value = "修改项目信息")
    @LogAnnotation(module = "修改项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemBasic itemBasic){
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemBasic.setEdit_userid(userId);
        itemBasic.setEdit_date(new Date());
        itemBasicService.update(itemBasic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @ApiOperation(value = "删除项目信息")
    @LogAnnotation(module = "删除项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
        itemBasicService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @ApiOperation(value = "废弃项目")
    @LogAnnotation(module = "废弃项目")
    @PreAuthorize("hasAnyAuthority('project:item:discard')")
    @PutMapping("/discard/{id}")
    public ResponseEntity<Void> discardItem(@PathVariable Integer id){
        itemBasicService.discardItem(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加更改申请")
    @LogAnnotation(module = "添加更改申请")
    @PostMapping("/modifyApply")
    public ResponseEntity<modifyApply> ModifyApply(@RequestBody modifyApply modifyApply) {
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        modifyApply.setUser_id(userId);
        modifyApply.setApply_time(new Date());
        modifyApply.setStatus(0);
        modifyApplyService.save(modifyApply);
        return  ResponseEntity.status(HttpStatus.CREATED).body(modifyApply);
    }

    @ApiOperation(value = "查询更改申请")
    @LogAnnotation(module = "查询更改申请")
    @GetMapping("/modifyApply/list")
    public ResponseEntity<List<modifyApply>> getApplyById(@RequestParam Map<String, Object> params){
        List<modifyApply> targets = modifyApplyService.findListByParams(params,modifyApply.class);

        return ResponseEntity.ok(targets);
    }

    @ApiOperation(value = "修改更改申请")
    @LogAnnotation(module = "修改更改申请")
    @PutMapping("/modifyApply")
    public ResponseEntity<Void> updateApply(@RequestBody modifyApply modifyApply){
        modifyApplyService.update(modifyApply);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询更改申请")
    @LogAnnotation(module = "查询更改申请")
    @GetMapping("/modifyApply/lists")
    public ResponseEntity<List<modifyApply>> getApplyCheck(@RequestParam Map<String, Object> params){
        List<modifyApply> targets = modifyApplyService.findApplyList(params,modifyApply.class);
        System.out.println(targets);
        return ResponseEntity.ok(targets);
    }

    @LogAnnotation(module = "获取更改申请列表")
    @GetMapping("/modifyApply/page")
    public ResponseEntity<PageResult<modifyApply>> getModifyApplyAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                            @RequestParam(required = false) Map<String, Object> params){
        String name = (String) params.get("item_name");
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
        PageResult<modifyApply> result = modifyApplyService.queryList(page,rows,sortBy,desc,params);
        return ResponseEntity.ok(result);
    }
}
