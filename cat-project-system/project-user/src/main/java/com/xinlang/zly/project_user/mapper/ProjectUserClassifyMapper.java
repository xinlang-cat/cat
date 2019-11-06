package com.xinlang.zly.project_user.mapper;

import com.xinlang.zly.project_user.bean.ProjectUserClassify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@Mapper
@Repository("projectUserClassifyMapper")
public interface ProjectUserClassifyMapper extends tk.mybatis.mapper.common.Mapper<ProjectUserClassify> {
}
