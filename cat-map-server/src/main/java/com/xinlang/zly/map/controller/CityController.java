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
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/city/all/{provinceCode}")
    @ApiOperation(value = "根据省份编码获取城市")
    @LogAnnotation(module = "根据省份编码获取城市")
    public List<City> findAllByProvinceCode(@PathVariable String provinceCode){
        return cityService.findByProvinceCode(provinceCode);
    }

    @GetMapping("/city/one/{cityCode}")
    @ApiOperation(value = "根据城市编码获取城市")
    @LogAnnotation(module = "根据城市编码获取城市")
    public City findCityByCityCode(@PathVariable String cityCode){
        return cityService.findByCityCode(cityCode);
    }

}
