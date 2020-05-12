package com.xinlang.zly.summary.service;

import com.xinlang.zly.summary.bean.ExpertEvaluate;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

public interface IExpertEvaluateService extends IBaseService<ExpertEvaluate> {
    void delByItemId(Integer itemId);
}
