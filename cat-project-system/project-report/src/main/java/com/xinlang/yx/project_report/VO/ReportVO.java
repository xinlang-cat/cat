package com.xinlang.yx.project_report.VO;

import com.xinlang.yx.project_report.bean.ReportFile;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Data
public class ReportVO {
    private Integer id;//id

    private Integer projectId;//项目id

    private String title;//报告标题

    private String content;//报告内容

    private Date createTime;//创建时间

    private Long createUserId;//创建人id

    private Integer status;//状态

    private Date startDay;//开始时间

    private Date finishDay;//结束时间

    private List<ReportFile> files;
}
