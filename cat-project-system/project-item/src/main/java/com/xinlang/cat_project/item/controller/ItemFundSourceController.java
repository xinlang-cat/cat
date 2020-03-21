package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemFundSourceService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item/fundSource")
public class ItemFundSourceController {
    @Autowired
    private IItemFundSourceService itemFundSourceService;

    @ApiOperation(value = "查询进度安排")
    @LogAnnotation(module = "查询进度安排")
    @GetMapping("list")
    public ResponseEntity<List<ItemFundSource>> getFundSource(@RequestParam Map<String, Object> params){
        List<ItemFundSource> fundSources = itemFundSourceService.findListByParams(params,ItemFundSource.class);
        return ResponseEntity.ok(fundSources);
    }

}
