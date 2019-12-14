package com.xinlang.cat_project.item.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.cat_project.item.VO.ItemUserInfo;
import com.xinlang.cat_project.item.fegin.ConsumeProjectUser;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemUserService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private ConsumeProjectUser consumeProjectUser;

    @ApiOperation(value = "添加一条成员")
    @LogAnnotation(module = "添加一条成员")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @Transactional
    @PostMapping("/one")
    public ResponseEntity<Void> saveItemUser(@RequestBody ItemUser itemUser) {
        itemUserService.save(itemUser);
        List<Integer> targetIds = itemUser.getTargetIds();
        for (Integer targetId : targetIds) {
            itemUserService.insertTargetUser(itemUser.getItem_id(),targetId,itemUser.getUser_id());
        }
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "添加多条成员")
    @LogAnnotation(module = "添加多条成员")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping("/multi")
    public ResponseEntity<Void> saveContents(@RequestBody List<ItemUser> itemUsers) {
        itemUserService.saveitemUsers(itemUsers);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询成员以及相关指标")
    @LogAnnotation(module = "查询成员以及相关指标")
    @GetMapping("list")
    @Transactional
    public ResponseEntity<List<Map<String,Object>>> getItemUserInfoById(@RequestParam Map<String, Object> params){
        List<Map<String, Object>> list = new ArrayList<>();
        List<ItemUser> itemUsers = itemUserService.findListByParams(params, ItemUser.class);
        for (ItemUser u : itemUsers) {
            Map<String, Object> ItemUserInfo = new HashMap<>();
            //List<ProjectUser> projectUser = consumeProjectUser.findByUserId(u.getUser_id());
            List<Integer> targetIds = itemUserService.selectTargetUserByUserId(u.getItem_id(), u.getUser_id());
            ItemUserInfo.put("itemUser",u);
            ItemUserInfo.put("targetIds",targetIds);
            list.add(ItemUserInfo); //添加进list
        }
        return ResponseEntity.ok(list);
    }

    @ApiOperation(value = "修改成员")
    @LogAnnotation(module = "修改成员")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping("/one")
    public ResponseEntity<Void> updateItemUser(@RequestBody ItemUser itemUser){
        itemUserService.update(itemUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "修改多条成员")
    @LogAnnotation(module = "修改多条成员")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping("/multi")
    public ResponseEntity<Void> updateItemUsers(@RequestBody List<ItemUser> itemUsers){
        for (ItemUser itemContent : itemUsers) {
            itemUserService.DeleteTargetUser(itemContent.getId());
            itemUserService.delete(itemContent.getId());
        }
        itemUserService.saveitemUsers(itemUsers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除成员")
    @LogAnnotation(module = "删除成员")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemUser(@PathVariable Integer id){
        //先删除人员与指标的关系
        itemUserService.DeleteTargetUser(id);
        //再删除人员
        itemUserService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
