package com.xinlang.zly.label.service.impl;

import com.xinlang.zly.label.bean.PolicyFile;
import com.xinlang.zly.label.mapper.PolicyFileMapper;
import com.xinlang.zly.label.service.IPolicyFileService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PolicyFileService extends BaseService<PolicyFile> implements IPolicyFileService {
    @Autowired
    private PolicyFileMapper policyFilemapper;
}
