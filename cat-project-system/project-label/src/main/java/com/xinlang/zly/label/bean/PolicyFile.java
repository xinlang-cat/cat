package com.xinlang.zly.label.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "project_policy_file")
public class PolicyFile implements Serializable {

    private static final long serialVersionUID = 1511493084535163723L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String fileId;
    private String fileUrl;
    private String fileName;
    private Integer createUserId;
    private String createUserName;
    private Date upDate;
    private String remark;
}
