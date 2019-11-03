package com.xinlang.zly.map.service;


import com.xinlang.zly.map.bean.City;

import java.util.List;

public interface ICityService{
	List<City> findByProvinceCode(String ProvinceCode);
	City findByCityCode(String cityCode);
}
