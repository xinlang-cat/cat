package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_fund_detail")
public class ItemFundDetail {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private Integer budget_id;
    private Integer money;
    private Date date;
    private String remark;
    private String accessory;
    private Integer status;
    private Integer edit_user_id;
    private  String operator; //经办人
}
