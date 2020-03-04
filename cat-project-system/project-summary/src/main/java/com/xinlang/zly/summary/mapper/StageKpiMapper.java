package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.StageKpi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("stageKpiMapper")
public interface StageKpiMapper extends tk.mybatis.mapper.common.Mapper<StageKpi> {
}
