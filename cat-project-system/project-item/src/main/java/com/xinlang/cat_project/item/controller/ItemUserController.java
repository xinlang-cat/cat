package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.VO.ItemUserInfo;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Void> saveItemUser(@RequestBody ItemUser itemUser) {

        itemUserService.saveItemUser(itemUser);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条项目成员数据（搁置）
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemUserInfo> getItemUserInfoById(@PathVariable Integer id){

        ItemUserInfo itemUserInfo = itemUserService.queryItemUserInfoById(id);
        return ResponseEntity.ok(itemUserInfo);
    }

    /**
     * 获取当前项目的所有成员数据（搁置）
     * @param Iid 项目id
     * @return
     */
    @GetMapping("/{Iid}")
    public ResponseEntity<ItemUserInfo> getItemUserInfoAllByIId(@PathVariable Integer Iid){

        ItemUserInfo itemUserInfo = itemUserService.queryItemUserInfoById(Iid);
        return ResponseEntity.ok(itemUserInfo);
    }

    /**
     * 更改
     * @param itemUser
     * @return
     */
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemUser(@PathVariable Integer id){
        itemUserService.deleteItemUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
