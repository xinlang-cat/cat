package com.xinlang.cat_project.item.VO;

import com.xinlang.cat_project.item.pojo.ItemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 项目人员详情
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemUserInfo {

    public ItemUser itemUser;
    public String name;
    public String gender;
    public Integer age;
    public String identityCard;
    public String jobTitle;
    public String professional;
    public String workUnit;
}
