package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "project_summary_stage_affix_file")
public class StageAffixFile implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;//报告id
    private String fileId;//文件id
    private String fileUrl;//文件路径
    private String fileRemark;//文件说明
}
