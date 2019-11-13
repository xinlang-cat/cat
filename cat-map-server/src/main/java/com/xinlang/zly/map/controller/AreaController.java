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
import org.springframework.security.access.prepost.PreAuthorize;
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
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping("/area/all/{cityCode}")
    @ApiOperation(value = "根据城市代码获取区县")
    @LogAnnotation(module = "添根据城市代码获取区县")
    public List<Area> findAllByCityCode(@PathVariable String cityCode){
        return areaService.findByCityCode(cityCode);
    }

    @GetMapping("/area/one/{areaCode}")
    @ApiOperation(value = "根据区县编码获取区县")
    @LogAnnotation(module = "根据区县编码获取区县")
    public Area findByAreaCode(@PathVariable String areaCode){
        return areaService.findByAreaCode(areaCode);
    }
}
