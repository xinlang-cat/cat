package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.WorkLogAffiliate;
import com.xinlang.zly.summary.mapper.WorkLogAffiliateMapper;
import com.xinlang.zly.summary.service.IWorkLogAffiliateService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkLogAffiliateService extends BaseService<WorkLogAffiliate> implements IWorkLogAffiliateService {
    @Autowired
    private WorkLogAffiliateMapper workLogAffiliateMapper;
    @Override
    public List<WorkLogAffiliate> findLatelyByItemId(Integer itemId) {
        return workLogAffiliateMapper.findLatelyByItemId(itemId);
    }
}
