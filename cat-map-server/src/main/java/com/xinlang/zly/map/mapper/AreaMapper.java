package com.xinlang.zly.map.mapper;


import com.xinlang.zly.map.bean.Area;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository("areaMapper")
public interface AreaMapper extends Mapper<Area> {
}