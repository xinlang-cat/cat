package com.xinlang.bean.project_user;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 所能服务的具体产业或领域
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Data
@Table(name = "project_user_domain")
public class ProjectUserDomain implements Serializable {

    private static final long serialVersionUID = 5444507844212690899L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private Integer userType;//用户类型
    private String labelSign;//领域标签代码
    private Date createTime;
    private Date updateTime;
}
