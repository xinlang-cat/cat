package com.xinlang.zly.project_user.mapper;

import com.xinlang.bean.project_user.ProjectUserSkill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Mapper
@Repository("projectUserSkillMapper")
public interface ProjectUserSkillMapper extends tk.mybatis.mapper.common.Mapper<ProjectUserSkill> {
}
