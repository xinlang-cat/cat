package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.AuditApplyMapper;
import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.pojo.auditApply;
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
public class AuditApplyService extends BaseService<auditApply> implements IAuditApplyService {

    @Autowired
    private AuditApplyMapper auditApplyMapper;


    @Override
    public void add(auditApply auditApply) {
        auditApplyMapper.insert(auditApply);
    }

    @Override
    public List<auditApply> findApplyList(Map<String, Object> params, Class<auditApply> auditApplyClass) {
        Example example = new Example(auditApply.class);
       if (params.get("status") != ""){
            example.createCriteria().andEqualTo("status",params.get("status"));
        }else {
            example.createCriteria().andNotEqualTo("status",0);
        }
        List<auditApply> list =auditApplyMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageResult<auditApply> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(auditApply.class);
        String item_ids = (String) params.get("item_ids");
        List list1 = new ArrayList();
        if (item_ids != null && item_ids.length() != 0) {
            String[] ids = item_ids.split(",");
            if (ids.length != 0) {
                for (int i = 0; i < ids.length; i++) {
                    list1.add(Integer.parseInt(ids[i]));
                }
            }
            //有项目，有状态
            if (params.get("status") != "") {
                example.createCriteria().andEqualTo("status", params.get("status")).andIn("item_id", list1);
            } else {
                //有项目，没有状态
                example.createCriteria().andIn("item_id", list1).andNotEqualTo("status", 0);
            }

        } else {
            //没有项目
            example.createCriteria().andEqualTo("item_id", -1);
        }




        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<auditApply> list =auditApplyMapper.selectByExample(example);
        //解析分页结果
        PageInfo<auditApply> info = new PageInfo<>(list);

        return  new PageResult<>(200, info.getTotal(), list);

    }
}
