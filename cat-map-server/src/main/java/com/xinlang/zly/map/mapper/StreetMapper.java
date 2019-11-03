package com.xinlang.zly.map.mapper;


import com.xinlang.zly.map.bean.Street;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
@Repository("streetMapper")
public interface StreetMapper extends Mapper<Street> {

}