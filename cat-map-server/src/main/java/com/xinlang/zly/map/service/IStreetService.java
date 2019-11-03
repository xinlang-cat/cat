package com.xinlang.zly.map.service;

import com.xinlang.zly.map.bean.Street;
import java.util.List;
public interface IStreetService {

	List<Street>  findByAreaCode(String areaCode);

	Street findByStreetCode(String streetCode);

}
