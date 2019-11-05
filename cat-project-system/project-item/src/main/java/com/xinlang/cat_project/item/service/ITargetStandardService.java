package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.TargetStandard;

import java.util.List;

public interface ITargetStandardService {
    
    void saveTargetStandard(TargetStandard targetStandard);

    TargetStandard queryTargetStandardById(Integer id);

    List<TargetStandard> queryTargetStandardByTId(Integer tid);

    void updateTargetStandard(TargetStandard targetStandard);

    void deleteTargetStandard(Integer id);
}
