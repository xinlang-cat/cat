package com.xinlang.zly.map.service.impl;

import com.xinlang.zly.map.bean.Province;
import com.xinlang.zly.map.mapper.ProvinceMapper;
import com.xinlang.zly.map.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProvinceService  implements IProvinceService {

	@Autowired
	private ProvinceMapper provinceMapper;


	@Override
	public List<Province> findAll() {
		return provinceMapper.selectAll();
	}

	@Override
	public Province findByProvinceCode(String provinceCode) {
		Example example = new Example(Province.class);
		example.createCriteria().andEqualTo("provinceCode",provinceCode);
		List<Province> list =  provinceMapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}
}
