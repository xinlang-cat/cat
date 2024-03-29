package com.xinlang.cat_project.item.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.ModifyApplyMapper;
import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.cat_project.item.service.IModifyApplyService;
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
public class ModifyApplyService extends BaseService<modifyApply> implements IModifyApplyService {

    @Autowired
    private ModifyApplyMapper modifyApplyMapper;


    @Override
    public List<modifyApply> findApplyList(Map<String, Object> params, Class<modifyApply> modifyApplyClass) {
        Example example = new Example(modifyApply.class);
        if (params.get("status") != "" && params.get("item_id") != ""){
            example.createCriteria().andEqualTo("status",params.get("status")).andEqualTo("item_id",params.get("item_id"));
        }else {
            example.createCriteria().andNotEqualTo("status",-1).andEqualTo("item_id",params.get("item_id"));
        }
        List<modifyApply> list =modifyApplyMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageResult<modifyApply> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(modifyApply.class);
        String item_ids = (String) params.get("item_ids");
        List list1 = new ArrayList();
        if (item_ids != null && item_ids.length() != 0) {
            String[] ids = item_ids.split(",");
            if (ids.length != 0) {
                for (int i = 0; i < ids.length; i++) {
                    list1.add(Integer.parseInt(ids[i]));
                }
            }
            //有状态有类型有项目
            if (params.get("status") != "" && params.get("type") != "") {
                example.createCriteria().andEqualTo("status", params.get("status")).andIn("item_id", list1).andEqualTo("type", params.get("type"));
            }else if (params.get("type") != "") {
                //有项目，有类型
                example.createCriteria().andEqualTo("type", params.get("type")).andIn("item_id", list1).andNotEqualTo("status", 0);
            }else if (params.get("status") != "") {
                //有项目，有状态
                example.createCriteria().andEqualTo("status", params.get("status")).andIn("item_id", list1);
            } else {
                //有项目，没有状态，没有类型
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
        List<modifyApply> list =modifyApplyMapper.selectByExample(example);
        //解析分页结果
        PageInfo<modifyApply> info = new PageInfo<>(list);
        return  new PageResult<>(200, info.getTotal(), list);
    }
}
