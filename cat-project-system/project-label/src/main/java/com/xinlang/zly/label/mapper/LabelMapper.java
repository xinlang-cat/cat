package com.xinlang.zly.label.mapper;


import com.xinlang.zly.label.bean.Label;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-10-31
 */
@Mapper
@Repository("labelMapper")
public interface LabelMapper extends tk.mybatis.mapper.common.Mapper <Label>{
}
