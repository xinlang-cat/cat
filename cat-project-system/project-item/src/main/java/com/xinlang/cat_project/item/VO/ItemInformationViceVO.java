package com.xinlang.cat_project.item.VO;

import com.xinlang.cat_project.item.pojo.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemInformationViceVO {

    private modifyApply modifyApply;
    private ItemInformationVice information;
    private List<ItemIndicatorsVice> indicators;
    private List<ItemIndicatorsVice> achievements;
    private List<ItemSchedulingVice> scheduling;
    private List<ItemPersonnelVice> personnels;
    private List<ItemFundBudgetVice> fundBudgets;
    private ItemFundSourceVice fundSource;
    private List<ItemContactWayVice> contactWays;
}
