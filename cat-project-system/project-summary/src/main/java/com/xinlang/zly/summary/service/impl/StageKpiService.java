package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.StageKpi;
import com.xinlang.zly.summary.service.IStageKpiService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageKpiService extends BaseService<StageKpi> implements IStageKpiService {
}
