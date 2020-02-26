package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 * @author 杨珣
 * 2020/2/4
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modify_apply")
public class modifyApply implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String item_id;
    private String content;
    private Integer user_id;
    private Integer change_user;
    private Integer modify;
    private Date apply_time;
    private Date modify_time;
    private Integer check_user;
    private Integer status;
}
