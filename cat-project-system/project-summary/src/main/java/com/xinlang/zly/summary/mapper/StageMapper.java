package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.Stage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-30
 */
@Mapper
@Repository("stageMapper")
public interface StageMapper extends tk.mybatis.mapper.common.Mapper<Stage> {
}
