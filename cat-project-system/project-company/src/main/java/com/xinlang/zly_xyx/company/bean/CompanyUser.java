package com.xinlang.zly_xyx.company.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-02
 */
@Data
@Table(name="company_user")
public class CompanyUser implements Serializable {
    private static final long serialVersionUID = 3594392707554059829L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;//用户id
    private String deptCode;//公司代码
    /**
     * 暂时不用
     */
    private String branchId;//部门id
}
