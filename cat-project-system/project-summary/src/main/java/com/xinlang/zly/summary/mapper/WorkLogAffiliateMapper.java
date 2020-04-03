package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.WorkLogAffiliate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("workLogAffiliateMapper")
public interface WorkLogAffiliateMapper extends tk.mybatis.mapper.common.Mapper<WorkLogAffiliate> {
    List<WorkLogAffiliate> findLatelyByItemId(Integer itemId);
}
