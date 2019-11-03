package com.xinlang.zly.map.service;


import com.xinlang.zly.map.bean.Province;

import java.util.List;

public interface IProvinceService{

	List<Province> findAll();

	Province findByProvinceCode(String provinceCode);
}
