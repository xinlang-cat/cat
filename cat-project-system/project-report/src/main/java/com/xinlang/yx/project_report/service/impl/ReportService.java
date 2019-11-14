package com.xinlang.yx.project_report.service.impl;

import com.xinlang.yx.project_report.VO.ReportVO;
import com.xinlang.yx.project_report.bean.Report;
import com.xinlang.yx.project_report.mapper.ReportMapper;
import com.xinlang.yx.project_report.service.IReportService;
import com.xinlang.yx.project_report.utils.constant;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Service
@Transactional
public class ReportService implements IReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public void save(Report report) {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        Long userId =  loginAppUser.getId();
        report.setCreateTime(new Date());
        report.setStatus(constant.reportStatus.PERSONAL);
        reportMapper.insert(report);
    }

    @Override
    public void update(Report report) {
        Example example = new Example(Report.class);
        example.createCriteria().andEqualTo("id",report.getId());
        reportMapper.updateByExampleSelective(report,example);

    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(Report.class);
        example.createCriteria().andEqualTo("id",id);
        reportMapper.deleteByExample(example);
    }

    @Override
    public List<Report> findByProId(Integer projectId) {
        Example example = new Example(Report.class);
        example.createCriteria().andEqualTo("projectId",projectId);
        return reportMapper.selectByExample(example);
    }

    @Override
    public Report find(Integer id) {
        Example example = new Example(Report.class);
        example.createCriteria().andEqualTo("id",id);
        List<Report>  lists = reportMapper.selectByExample(example);
        if(lists != null){
            return lists.get(0);
        }

        return null;
    }

    @Override
    public ReportVO findVO(Integer id) {
        return reportMapper.findVOByID(id);
    }

    @Override
    public List<ReportVO> findVOByProId(Integer projectId) {
        return reportMapper.findVOByProjectID(projectId);
    }
}
