package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_personnel")
public class ItemPersonnel {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private Integer user_id;
    private String name;
    private String sex;
    private Integer age;
    private String professional_title; // 职称
    private String specialty;        // 从事专业
    private String organization;     // 工作单位
    private String responsibilities; // 责任分工
}
