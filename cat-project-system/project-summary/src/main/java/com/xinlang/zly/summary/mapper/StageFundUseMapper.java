package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.StageFundUse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("stageFundUseMapper")
public interface StageFundUseMapper extends tk.mybatis.mapper.common.Mapper<StageFundUse> {
}
