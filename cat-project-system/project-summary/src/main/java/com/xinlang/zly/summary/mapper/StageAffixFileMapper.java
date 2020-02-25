package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.StageAffixFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("stageAffixFileMapper")
public interface StageAffixFileMapper extends tk.mybatis.mapper.common.Mapper<StageAffixFile> {
}
