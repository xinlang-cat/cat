package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 标准（比如经济指标中的各项标准）
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "target_standard")
public class TargetStandard {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer target_id;
    private String node;
    private Integer amount;
    private String remark;
    private Integer status;
}
