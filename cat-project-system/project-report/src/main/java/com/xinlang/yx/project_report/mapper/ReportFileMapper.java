package com.xinlang.yx.project_report.mapper;

import com.xinlang.yx.project_report.bean.ReportFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Mapper
@Repository("reportFileMapper")
public interface ReportFileMapper  extends tk.mybatis.mapper.common.Mapper<ReportFile>{
}
