package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.mapper.AuditApplyMapper;
import com.xinlang.cat_project.item.mapper.AuditApplyResultMapper;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.cat_project.item.pojo.auditApplyResult;
import com.xinlang.cat_project.item.service.IAuditApplyResultService;
import com.xinlang.cat_project.item.service.IAuditApplyService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class AuditApplyResultService extends BaseService<auditApplyResult> implements IAuditApplyResultService {

    @Autowired
    private AuditApplyResultMapper auditApplyMapper;


    @Override
    public void add(auditApplyResult auditApplyResult) {
        auditApplyMapper.insert(auditApplyResult);
    }

    @Override
    public List<auditApplyResult> findApplyList(Map<String, Object> params, Class<auditApplyResult> auditApplyResultClass) {
        Example example = new Example(auditApplyResult.class);
       if (params.get("status") != ""){
            example.createCriteria().andEqualTo("status",params.get("status"));
        }else {
            example.createCriteria().andNotEqualTo("status",0);
        }
        List<auditApplyResult> list =auditApplyMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageResult<auditApplyResult> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(auditApplyResult.class);

         example.createCriteria().andEqualTo("expert_id", params.get("expert_id"));


        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<auditApplyResult> list =auditApplyMapper.selectByExample(example);
        //解析分页结果
        PageInfo<auditApplyResult> info = new PageInfo<>(list);

        return  new PageResult<>(200, info.getTotal(), list);

    }

    @Override
    public Integer finUnChecked(Integer auditApply_id) {

        Example example = new Example(auditApplyResult.class);

        example.createCriteria().andEqualTo("audit_apply_id", auditApply_id).andEqualTo("status",1);


        //查询
        List<auditApplyResult> list =auditApplyMapper.selectByExample(example);
       return list.size();
    }
}
