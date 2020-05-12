package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.ExpertEvaluate;
import com.xinlang.zly.summary.bean.ExpertEvaluateAffiliate;
import com.xinlang.zly.summary.mapper.ExpertEvaluateAffiliateMapper;
import com.xinlang.zly.summary.mapper.ExpertEvaluateMapper;
import com.xinlang.zly.summary.service.IExpertEvaluateService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class ExpertEvaluateService extends BaseService<ExpertEvaluate> implements IExpertEvaluateService {
    @Autowired
    private ExpertEvaluateMapper expertEvaluateMapper;
    @Autowired
    private ExpertEvaluateAffiliateMapper expertEvaluateAffiliateMapper;
    public  void delByItemId(Integer itemId){
        Example example = new Example(ExpertEvaluate.class);
        example.createCriteria().andEqualTo("itemId",itemId);
        expertEvaluateMapper.deleteByExample(example);
        Example example1 = new Example(ExpertEvaluateAffiliate.class);
        example1.createCriteria().andEqualTo("itemId",itemId);
        expertEvaluateAffiliateMapper.deleteByExample(example1);
    }
}
