package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.fegin.ConsumeUser;
import com.xinlang.cat_project.item.pojo.ItemFundUse;
import com.xinlang.cat_project.item.service.IItemFundUseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.LoginAppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item/fund/use")
public class ItemFundUseController {

    @Autowired
    private IItemFundUseService itemFundUseService;

    @Autowired
    private ConsumeUser consumeUser;

    @ApiOperation(value = "添加资金使用信息")
    @LogAnnotation(module = "添加资金使用信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @Transactional
    @PostMapping
    public ResponseEntity<Void> saveFundUse(@RequestBody ItemFundUse itemFundUse) {
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemFundUse.setEdit_userid(userId);
        itemFundUse.setEdit_date(new Date());
        //保存
        itemFundUseService.save(itemFundUse);
        //保存凭据
        List<String> bills = itemFundUse.getBill_url();
        for (String bill : bills) {
            itemFundUseService.saveBill(itemFundUse.getId(),bill);
        }
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "获取资金使用信息")
    @LogAnnotation(module = "获取资金使用信息")
    @GetMapping("/list")
    public ResponseEntity<List<ItemFundUse>> getFundById(@RequestParam Map<String, Object> params){

        List<ItemFundUse> itemFundUses = itemFundUseService.findListByParams(params, ItemFundUse.class);
        for (ItemFundUse itemFundUs : itemFundUses) {
            List<String> bills = itemFundUseService.findBill(itemFundUs.getId());
            itemFundUs.setBill_url(bills);
        }
        return ResponseEntity.ok(itemFundUses);
    }


    @ApiOperation(value = "更改资金使用信息")
    @LogAnnotation(module = "更改资金使用信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @Transactional
    @PutMapping
    public ResponseEntity<Void> updateFund(@RequestBody ItemFundUse itemFundUse){
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemFundUse.setEdit_userid(userId);
        itemFundUse.setEdit_date(new Date());
        //修改
        itemFundUseService.update(itemFundUse);
        //修改凭据
        List<String> bills = itemFundUse.getBill_url();
        for (String bill : bills) {
            itemFundUseService.updateBill(itemFundUse.getId(),bill);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除资金使用信息")
    @LogAnnotation(module = "删除资金使用信息")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundUse(@PathVariable Integer id){
        itemFundUseService.delete(id);
        itemFundUseService.deleteBill(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
