package com.xinlang.yx.project_report.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Data
@Table(name = "report_files")
public class ReportFile {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    //报告id
    private Integer reportId;
    //文件路径
    private  String fileUrl;
}
