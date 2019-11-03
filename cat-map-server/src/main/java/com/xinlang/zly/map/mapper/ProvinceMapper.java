package com.xinlang.zly.map.mapper;


import com.xinlang.zly.map.bean.Province;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
@Repository("provinceMapper")
public interface ProvinceMapper extends Mapper<Province> {
}