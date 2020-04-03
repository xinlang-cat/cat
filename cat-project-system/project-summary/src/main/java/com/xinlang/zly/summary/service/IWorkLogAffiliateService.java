package com.xinlang.zly.summary.service;

import com.xinlang.zly.summary.bean.WorkLogAffiliate;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IWorkLogAffiliateService extends IBaseService<WorkLogAffiliate> {
    List<WorkLogAffiliate> findLatelyByItemId(Integer itemId);
}
