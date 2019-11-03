package com.xinlang.zly.map.service.impl;

import com.xinlang.zly.map.bean.City;
import com.xinlang.zly.map.mapper.CityMapper;
import com.xinlang.zly.map.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CityService implements ICityService {

	@Autowired
	private CityMapper cityMapper;

	@Override
	public List<City> findByProvinceCode(String provinceCode) {
		Example example = new Example(City.class);
		example.createCriteria().andEqualTo("provinceCode",provinceCode);
		return cityMapper.selectByExample(example);
	}

	@Override
	public City findByCityCode(String cityCode) {
		Example example = new Example(City.class);
		example.createCriteria().andEqualTo("cityCode",cityCode);
		return cityMapper.selectByExample(example).get(0);
	}
}
