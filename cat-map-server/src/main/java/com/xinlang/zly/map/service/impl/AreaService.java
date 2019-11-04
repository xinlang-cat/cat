package com.xinlang.zly.map.service.impl;

import com.xinlang.zly.map.bean.Area;
import com.xinlang.zly.map.mapper.AreaMapper;
import com.xinlang.zly.map.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AreaService implements IAreaService {
	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> findByCityCode(String cityCode) {
		Example example = new Example(Area.class);
		example.createCriteria().andEqualTo("cityCode",cityCode);
		return areaMapper.selectByExample(example);
	}

	@Override
	public Area findByAreaCode(String areaCode) {
		Example example = new Example(Area.class);
		example.createCriteria().andEqualTo("areaCode",areaCode);
		List<Area> list = areaMapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}
}
