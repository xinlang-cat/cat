package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemFundDetail;
import com.xinlang.cat_project.item.service.IItemFundDetailService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item/fundDetail")
public class ItemFundDetailController {
    @Autowired
    private IItemFundDetailService itemFundDetailService;

    @ApiOperation(value = "添加经费明细")
    @LogAnnotation(module = "添加经费明细")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemFundDetail> saveFundDetail(@RequestBody ItemFundDetail fundDetail) {
        itemFundDetailService.save(fundDetail);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询经费明细")
    @LogAnnotation(module = "查询经费明细")
    @GetMapping("list")
    public ResponseEntity<List<ItemFundDetail>> getFundDetail(@RequestParam Map<String, Object> params){
        List<ItemFundDetail> fundDetail = itemFundDetailService.findListByParams(params,ItemFundDetail.class);
        return ResponseEntity.ok(fundDetail);
    }
    @ApiOperation(value = "修改经费明细")
    @LogAnnotation(module = "修改经费明细")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ItemFundDetail itemFundDetail){
        itemFundDetailService.update(itemFundDetail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @ApiOperation(value = "删除经费明细")
    @LogAnnotation(module = "删除经费明细")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        itemFundDetailService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
