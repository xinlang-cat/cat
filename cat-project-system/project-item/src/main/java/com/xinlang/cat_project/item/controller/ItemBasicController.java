package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.service.IItemBasicService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询项目，分页查询
     * @param page 页数
     * @param rows 行数
     * @param sortBy 排序依据 (预留，暂时没用到)
     * @param desc 排序 (预留，暂时没用到)
     * @param params 参数
     * @return
     */
    @LogAnnotation(module = "获取项目列表")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ItemBasic>> getItemAll(@RequestParam(value = "start", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "length", defaultValue = "10") Integer rows,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                            @RequestParam(required = false) Map<String, Object> params){

        if (page==0){
            page++;
        }else {
            page/=10;
            page++;
        }
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
    /**
     * 获取当前用户的公司项目数据
     * @return
     */
    @ApiOperation(value = "查询当前用户的公司项目")
    @LogAnnotation(module = "查询当前用户的公司项目")
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
}
