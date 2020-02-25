package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.StagePaperCatalogue;
import com.xinlang.zly.summary.service.IStagePaperCatalogueService;
import com.xinlang.zly.summary.service.IStagePatentCatalogueService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StagePaperCatalogueService extends BaseService<StagePaperCatalogue> implements IStagePaperCatalogueService {
}
