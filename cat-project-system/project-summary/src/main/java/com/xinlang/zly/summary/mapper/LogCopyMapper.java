package com.xinlang.zly.summary.mapper;

import com.xinlang.bean.util.LogCopy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("logCopyMapper")
public interface LogCopyMapper extends tk.mybatis.mapper.common.Mapper<LogCopy> {
}
