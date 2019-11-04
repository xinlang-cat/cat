package com.xinlang.zly.map.service.impl;

import com.xinlang.zly.map.bean.Street;
import com.xinlang.zly.map.mapper.StreetMapper;
import com.xinlang.zly.map.service.IStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

@Service
public class StreetService implements IStreetService {

	@Autowired
	private StreetMapper streetMapper;


	@Override
	public List<Street> findByAreaCode(String areaCode) {
		Example example = new Example(Street.class);
		example.createCriteria().andEqualTo("areaCode",areaCode);
		return streetMapper.selectByExample(example);
	}

	@Override
	public Street findByStreetCode(String streetCode) {
		Example example = new Example(Street.class);
		example.createCriteria().andEqualTo("streetCode",streetCode);
		List<Street>  list = streetMapper.selectByExample(example);
		return  list.isEmpty() ? null : list.get(0);
	}
}
