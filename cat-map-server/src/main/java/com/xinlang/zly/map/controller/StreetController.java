package com.xinlang.zly.map.controller;

import com.xinlang.zly.map.bean.Area;
import com.xinlang.zly.map.bean.City;
import com.xinlang.zly.map.bean.Province;
import com.xinlang.zly.map.bean.Street;
import com.xinlang.zly.map.service.IAreaService;
import com.xinlang.zly.map.service.ICityService;
import com.xinlang.zly.map.service.IProvinceService;
import com.xinlang.zly.map.service.IStreetService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-01
 */
@RestController
public class StreetController {

    @Autowired
    private IStreetService streetService;

    @GetMapping("/street/all/{areaCode}")
    @ApiOperation(value = "根据省份编码获取省份")
    @LogAnnotation(module = "根据省份编码码获取省份")
    public List<Street> findAllByAreaCode(@PathVariable String areaCode){
        return streetService.findByAreaCode(areaCode);
    }

    @GetMapping("/street/one/{streetCode}")
    @ApiOperation(value = "根据街道编码获取街道、镇")
    @LogAnnotation(module = "根据街道编码获取街道、镇")
    public Street findByStreetCode(@PathVariable String streetCode){
        return streetService.findByStreetCode(streetCode);
    }
}
