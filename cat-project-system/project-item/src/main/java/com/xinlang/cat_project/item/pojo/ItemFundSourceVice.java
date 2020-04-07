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
@Table(name = "item_fund_source_vice")
public class ItemFundSourceVice {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String responsible_unit;    //承担单位
    private Integer first_party_provide;    //甲方提供
    private Integer state_science_and_technology_department;    //国家科技部门拨款
    private Integer state_other_department; //国家其他部门拨款
    private Integer area_department;    //区级部门拨款
    private Integer city_department;    //市及部门拨款
    private Integer county_department;  //县级部门拨款
    private Integer own_fund;   //自有资金
    private Integer bank_loan;  //银行贷款
    private Integer offshore_fund;  //国外资金
    private Integer other_fund; //其他资金
}
