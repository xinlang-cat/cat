package com.xinlang.zly.project_user.mapper;

import com.xinlang.bean.project_user.ProjectUserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Mapper
@Repository("projectUserDomainMapper")
public interface ProjectUserDomainMapper extends tk.mybatis.mapper.common.Mapper<ProjectUserDomain> {
}
