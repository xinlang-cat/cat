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
@RequestMapping("/street")
public class StreetController {

    @Autowired
    private IStreetService streetService;

    /**
     * 根据区县代码获取街道、镇
     * @param areaCode
     * @return
     */
    @GetMapping("/all/{areaCode}")
    public List<Street> findAllByAreaCode(@PathVariable String areaCode){
        return streetService.findByAreaCode(areaCode);
    }

    /**
     * 根据代码获取街道、镇
     * @param streetCode
     * @return
     */
    @GetMapping("/one/{streetCode}")
    public Street findByStreetCode(@PathVariable String streetCode){
        return streetService.findByStreetCode(streetCode);
    }
}
