package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.SkillSummary;
import com.xinlang.zly.summary.service.ISkillSummaryService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-03
 */
@Service
@Transactional
public class SkillSummaryService extends BaseService<SkillSummary> implements ISkillSummaryService {
}
