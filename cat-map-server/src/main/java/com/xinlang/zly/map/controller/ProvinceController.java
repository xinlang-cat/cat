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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-01
 */
@RestController
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICityService cityService;
    @Autowired
    private IAreaService areaService;
    @Autowired
    private IStreetService streetService;

    @GetMapping("/province/all")
    @ApiOperation(value = "获取所有省份")
    @LogAnnotation(module = "根获取所有省份")
    public List<Province> findAll(){
        return provinceService.findAll();
    }

    @GetMapping("/province/one/{provinceCode}")
    @ApiOperation(value = "根据省份编码获取省份")
    @LogAnnotation(module = "根据省份编码码获取省份")
    public Province findByProvinceCode(@PathVariable String provinceCode){
        return provinceService.findByProvinceCode(provinceCode);
    }

    @GetMapping("/province/tree/{childCode}")
    @ApiOperation(value = "根据下级代码获取省份")
    @LogAnnotation(module = "根据下级代码获取省份")
    public Map<String,Object> findProvinceByChildCode(@PathVariable String childCode){
        Map<String,Object> map = new HashMap<>();
        Street street = streetService.findByStreetCode(childCode);
        if(street != null){
            map.put("street",street);
            Area area = areaService.findByAreaCode(street.getAreaCode());
            map.put("area",area);
            City city = cityService.findByCityCode(area.getCityCode());
            map.put("city",city);
            Province province = provinceService.findByProvinceCode(city.getProvinceCode());
            map.put("province",province);
            return map;
        }
        Area area = areaService.findByAreaCode(childCode);
        if(area != null){
            map.put("area",area);
            City city = cityService.findByCityCode(area.getCityCode());
            map.put("city",city);
            Province province = provinceService.findByProvinceCode(city.getProvinceCode());
            map.put("province",province);
            return map;
        }
        City city = cityService.findByCityCode(childCode);
        if(city != null){
            map.put("city",city);
            Province province = provinceService.findByProvinceCode(city.getProvinceCode());
            map.put("province",province);
            return map;
        }
        Province province = provinceService.findByProvinceCode(childCode);
        if(province != null){
            map.put("province",province);
            return map;
        }
        return null;
    }

}
