package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.ExpertEvaluate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("expertEvaluateMapper")
public interface ExpertEvaluateMapper extends tk.mybatis.mapper.common.Mapper<ExpertEvaluate> {
}
