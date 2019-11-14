package com.xinlang.yx.project_report.service;

import com.xinlang.yx.project_report.bean.ReportFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
public interface IReportFileService {
    void uploadFile(MultipartFile multipartFile, ReportFile reportFile);

    List<ReportFile> findByReportId(Integer reportId);

    void deleteByIds(Set<Integer> ids);
}
