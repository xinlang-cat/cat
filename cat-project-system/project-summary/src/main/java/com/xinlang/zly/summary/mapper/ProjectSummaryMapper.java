package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.ProjectSummary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@Mapper
@Repository("projectSummaryMapper")
public interface ProjectSummaryMapper extends tk.mybatis.mapper.common.Mapper<ProjectSummary> {
}
