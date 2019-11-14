package com.xinlang.yx.project_record.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Data
@Table(name = "journal_file")
public class RecordFile implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String url;

    private Long userId;

    private Date createTime;
}
