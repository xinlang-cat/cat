package com.xinlang.zly.map.service;


import com.xinlang.zly.map.bean.Area;

import java.util.List;

public interface IAreaService {
	List<Area>  findByCityCode(String cityCode);
	Area findByAreaCode(String areaCode);
}
