package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 *
 * @author 杨珣
 * 2020/2/4
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_apply_result")
public class auditApplyResult implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String item_id;
    private Integer audit_apply_id;
    private String target_id;
    /**
     * 审核专家id
     */
    private Integer expert_id;
    private Integer status;
    private Date start_date;
    private Date end_date;
}
