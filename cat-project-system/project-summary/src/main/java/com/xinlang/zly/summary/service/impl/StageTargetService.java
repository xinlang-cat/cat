package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.StageTarget;
import com.xinlang.zly.summary.service.IStageTargetService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StageTargetService extends BaseService<StageTarget> implements IStageTargetService {
}
