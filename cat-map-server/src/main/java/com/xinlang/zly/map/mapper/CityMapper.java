package com.xinlang.zly.map.mapper;

import com.xinlang.zly.map.bean.City;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
@Repository("cityMapper")
public interface CityMapper extends Mapper<City> {
}