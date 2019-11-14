package com.xinlang.yx.project_report.service.impl;

import com.xinlang.yx.project_report.bean.ReportFile;
import com.xinlang.yx.project_report.mapper.ReportFileMapper;
import com.xinlang.yx.project_report.service.IReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Service
@Transactional
public class ReportFileService implements IReportFileService {
    @Autowired
    private ReportFileMapper reportFileMapper;

    @Override
    public void uploadFile(MultipartFile multipartFile, ReportFile reportFile) {

    }

    @Override
    public List<ReportFile> findByReportId(Integer reportId) {
        Example example = new Example(ReportFile.class);
        example.createCriteria().andEqualTo("report_id",reportId);
        return reportFileMapper.selectByExample(example);
    }

    @Override
    public void deleteByIds(Set<Integer> ids) {
        Example example = new Example(ReportFile.class);
        example.createCriteria().andIn("id",ids);
        reportFileMapper.deleteByExample(example);

    }
}
