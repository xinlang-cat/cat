package com.xinlang.cat_project.item.VO;

import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.pojo.TargetStandard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TargetInfo {

    private Integer id;
    private ItemTarget target;
    //private UserInfo userInfo;
    private List<ItemUser> ItemUsers;
}
