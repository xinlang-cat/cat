package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.VO.ItemUserInfo;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemUserService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 项目组人员
 */
@RestController
@RequestMapping("/item/user")
public class ItemUserController {

    @Autowired
    private IItemUserService itemUserService;

    /**
     * 新增项目成员
     * @param itemUser
     * @return
     */
    @ApiOperation(value = "添加成员信息")
    @LogAnnotation(module = "添加成员信息")
    @PreAuthorize("hasAnyAuthority('project:item:user:save')")
    @PostMapping
    public ResponseEntity<Void> saveItemUser(@RequestBody ItemUser itemUser) {

        itemUserService.saveItemUser(itemUser);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条项目成员数据
     * @param id
     * @return
     */
    @ApiOperation(value = "获取一条成员信息")
    @LogAnnotation(module = "获取一条成员信息")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getItemUserInfoById(@PathVariable Integer id){

        Map<String,Object> itemUserInfo = itemUserService.queryItemUserInfoById(id);
        return ResponseEntity.ok(itemUserInfo);
    }

    /**
     * 获取当前项目的所有成员数据
     * @param Iid 项目id
     * @return
     */
    @ApiOperation(value = "获取项目的所有成员信息")
    @LogAnnotation(module = "获取项目的所有成员信息")
    @GetMapping("all/{Iid}")
    public ResponseEntity<List<Map<String,Object>>> getItemUserInfoAllByIId(@PathVariable Integer Iid){

        List<Map<String,Object>> itemUserInfo = itemUserService.queryItemUserInfoAllByIid(Iid);
        return ResponseEntity.ok(itemUserInfo);
    }

    /**
     * 更改
     * @param itemUser
     * @return
     */
    @ApiOperation(value = "修改成员信息， id必填")
    @LogAnnotation(module = "修改成员信息")
    @PreAuthorize("hasAnyAuthority('project:item:user:update')")
    @PutMapping
    public ResponseEntity<Void> updateItemUser(@RequestBody ItemUser itemUser){
        itemUserService.updateItemUser(itemUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除成员信息")
    @LogAnnotation(module = "删除成员信息")
    @PreAuthorize("hasAnyAuthority('project:item:user:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemUser(@PathVariable Integer id){
        itemUserService.deleteItemUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
