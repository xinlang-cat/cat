package com.xinlang.yx.project_report.service;

import com.xinlang.yx.project_report.VO.ReportVO;
import com.xinlang.yx.project_report.bean.Report;

import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
public interface IReportService {
    void save(Report report);

    void update(Report report);

    void delete(Integer id);

    List<Report> findByProId(Integer projectId);

    Report find(Integer id);

    ReportVO findVO(Integer id);

    List<ReportVO> findVOByProId(Integer projectId);
}
