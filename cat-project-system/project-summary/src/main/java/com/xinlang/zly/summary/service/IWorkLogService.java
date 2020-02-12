package com.xinlang.zly.summary.service;

import com.xinlang.zly.summary.bean.WorkLog;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-20
 */
public interface IWorkLogService extends IBaseService<WorkLog> {
    WorkLog findLatelyByItemIdAndTargetId(Integer itemId,Integer targetId);
}
