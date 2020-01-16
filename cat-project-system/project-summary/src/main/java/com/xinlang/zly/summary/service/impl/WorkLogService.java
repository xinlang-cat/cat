package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.WorkLog;
import com.xinlang.zly.summary.mapper.WorkLogMapper;
import com.xinlang.zly.summary.service.IWorkLogService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-20
 */
@Service
@Transactional
public class WorkLogService extends BaseService<WorkLog> implements IWorkLogService {
    @Autowired
    private WorkLogMapper workLogMapper;


    @Override
    public WorkLog findLatelyByItemIdAndTargetId(Integer itemId,Integer targetId) {
        return workLogMapper.findLatelyByItemIdAndTargetId(itemId,targetId);
    }
}
