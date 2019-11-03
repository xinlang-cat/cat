package com.xinlang.zly.map.controller;

import com.xinlang.zly.map.bean.Area;
import com.xinlang.zly.map.bean.City;
import com.xinlang.zly.map.bean.Province;
import com.xinlang.zly.map.bean.Street;
import com.xinlang.zly.map.service.IAreaService;
import com.xinlang.zly.map.service.ICityService;
import com.xinlang.zly.map.service.IProvinceService;
import com.xinlang.zly.map.service.IStreetService;
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
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private IAreaService areaService;


    /***
     * 根据城市代码获取区县
     * @param cityCode
     * @return
     */
    @GetMapping("/all/{cityCode}")
    public List<Area> findAllByCityCode(@PathVariable String cityCode){
        return areaService.findByCityCode(cityCode);
    }


    /***
     * 根据区县代码获取区县
     * @param areaCode
     * @return
     */
    @GetMapping("/one/{areaCode}")
    public Area findByAreaCode(@PathVariable String areaCode){
        return areaService.findByAreaCode(areaCode);
    }
}