package com.xinlang.zly.project_user.mapper;

import com.xinlang.bean.project_user.ProjectUserItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@org.apache.ibatis.annotations.Mapper
@Repository("projectUserItemMapper")
public interface ProjectUserItemMapper extends Mapper<ProjectUserItem> {
}
