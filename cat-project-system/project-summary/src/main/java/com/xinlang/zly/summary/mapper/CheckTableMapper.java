package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.CheckTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-17
 */
@Mapper
@Repository("checkTableMapper")
public interface CheckTableMapper extends tk.mybatis.mapper.common.Mapper<CheckTable>,InsertListMapper<CheckTable> {

}
