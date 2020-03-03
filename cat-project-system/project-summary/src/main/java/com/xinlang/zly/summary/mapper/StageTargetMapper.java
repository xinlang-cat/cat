package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.StageTarget;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("stageTargetMapper")
public interface StageTargetMapper extends tk.mybatis.mapper.common.Mapper<StageTarget> {
}
