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
public class ItemInformationVO {

    private ItemInformation information;
    private List<ItemIndicators> indicators;
    private List<ItemScheduling> scheduling;
    private List<ItemPersonnel> personnels;
    private List<ItemFundBudget> fundBudgets;
    private ItemFundSource fundSource;
    private List<ItemContactWay> contactWays;
}
