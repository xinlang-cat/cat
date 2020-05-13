package com.xinlang.zly.label.mapper;

import com.xinlang.zly.label.bean.PolicyFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("policyFileMapper")
public interface PolicyFileMapper extends tk.mybatis.mapper.common.Mapper<PolicyFile> {
}
