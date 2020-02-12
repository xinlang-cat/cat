package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.WorkLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-20
 */
@Mapper
@Repository("workLogMapper")
public interface WorkLogMapper extends tk.mybatis.mapper.common.Mapper<WorkLog> {
    WorkLog findLatelyByItemIdAndTargetId(Integer itemId,Integer targetId);
}
