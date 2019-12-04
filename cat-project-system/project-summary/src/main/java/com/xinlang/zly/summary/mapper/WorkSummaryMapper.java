package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.WorkSummary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@Mapper
@Repository("workSummaryMapper")
public interface WorkSummaryMapper extends tk.mybatis.mapper.common.Mapper<WorkSummary> {
}
