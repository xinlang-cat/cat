package com.xinlang.cat_project.item.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TargetInfoAll {

    private Integer id;
    private List<TargetInfo> targetInfos;
}
