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
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyAuthority('project:fundUse:save')")
    @PostMapping
    public ResponseEntity<Void> saveFundUse(@RequestBody ItemFundUse itemFundUse) {
        itemFundUseService.saveFundUse(itemFundUse);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 获取单条使用数据
     * @param id
     * @return
     */
    @ApiOperation(value = "获取一条资金使用信息，id 必填")
    @LogAnnotation(module = "获取一条资金使用信息")
    @GetMapping("/{id}")
    public ResponseEntity<ItemFundUse> getFundById(@PathVariable Integer id){

        ItemFundUse itemFundUse = itemFundUseService.queryFundUseById(id);
        return ResponseEntity.ok(itemFundUse);
    }

    /**
     * 获取预算的所有使用数据
     * @param Bid 预算id
     * @return
     */
    @ApiOperation(value = "获取预算的所有资金使用信息")
    @LogAnnotation(module = "获取预算的所有资金使用信息")
    @GetMapping("/info/{Bid}")
    public ResponseEntity<List<ItemFundUse>> getFundUseByBId(@PathVariable Integer Bid){

        List<ItemFundUse> itemFundUses = itemFundUseService.queryFundUseByBId(Bid);
        return ResponseEntity.ok(itemFundUses);
    }

    /**
     * 获取当前用户所保存的所有数据
     * @param
     * @return
     */
    @ApiOperation(value = "获取当前用户所保存的所有资金使用信息")
    @LogAnnotation(module = "获取当前用户所保存的所有资金使用信息")
    @GetMapping("/my/{Iid}")
    public ResponseEntity<List<ItemFundUse>> getFundUseByItemIdAndUserId(@PathVariable Integer Iid){

        //int UserTd = consumeUser.getLoginAppUser().getId().intValue();
        int UserTd = AppUserUtil.getLoginAppUser().getId().intValue();
        List<ItemFundUse> itemFundUses = itemFundUseService.queryFundUseByItemIdAndUserId(Iid,UserTd);
        return ResponseEntity.ok(itemFundUses);
    }

    /**
     * 更改
     * @param itemFundUse
     * @return
     */
    @ApiOperation(value = "更改资金使用信息,id必填")
    @LogAnnotation(module = "更改资金使用信息")
    @PreAuthorize("hasAnyAuthority('project:fundUse:update')")
    @PutMapping
    public ResponseEntity<Void> updateFund(@RequestBody ItemFundUse itemFundUse){
        itemFundUseService.updateFundUse(itemFundUse);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation(value = "删除资金使用信息")
    @LogAnnotation(module = "删除资金使用信息")
    @PreAuthorize("hasAnyAuthority('project:fundUse:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundUse(@PathVariable Integer id){
        itemFundUseService.deleteFundUse(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
