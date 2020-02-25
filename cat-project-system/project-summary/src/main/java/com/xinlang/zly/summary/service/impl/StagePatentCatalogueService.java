package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.StagePatentCatalogue;
import com.xinlang.zly.summary.service.IStagePatentCatalogueService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StagePatentCatalogueService extends BaseService<StagePatentCatalogue> implements IStagePatentCatalogueService {
}
