package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_fund_use")
public class ItemFundUse {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private Integer budget_id;
    private Float expenditure;
    private String remark;
    private Integer status;
    private Date edit_date;
    private Integer edit_userid;
    private Integer check_userid;
    private Date check_date;
    private String check_opinion;

    @Transient
    private List<Integer> bill_url;

    @Transient
    private String edit_dateStr;
    @Transient
    private String check_dateStr;

}
