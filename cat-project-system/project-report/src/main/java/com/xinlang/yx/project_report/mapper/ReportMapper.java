package com.xinlang.yx.project_report.mapper;

import com.xinlang.yx.project_report.VO.ReportVO;
import com.xinlang.yx.project_report.bean.Report;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Mapper
@Repository("reportMapper")
public interface ReportMapper extends tk.mybatis.mapper.common.Mapper<Report>{
    ReportVO findVOByID(Integer id);

    List<ReportVO> findVOByProjectID(Integer projectId);
}
