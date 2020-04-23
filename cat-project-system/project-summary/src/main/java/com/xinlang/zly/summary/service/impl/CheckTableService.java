package com.xinlang.zly.summary.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.bean.util.PageResult;
import com.xinlang.zly.summary.bean.CheckTable;
import com.xinlang.zly.summary.mapper.CheckTableMapper;
import com.xinlang.zly.summary.service.ICheckTableService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.common.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-17
 */
@Service
@Transactional
public class CheckTableService extends BaseService<CheckTable> implements ICheckTableService {
    @Autowired
    private CheckTableMapper checkTableMapper;

    @Override
    public PageResult<CheckTable> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(CheckTable.class);
        String item_ids = (String) params.get("item_ids");
        List list1 = new ArrayList();
        System.err.println("ids"+item_ids);
        if (item_ids != null && item_ids.length() != 0) {
            String[] ids = item_ids.split(",");
            if (ids.length != 0) {
                for (int i = 0; i < ids.length; i++) {
                    list1.add(Integer.parseInt(ids[i]));
                }
            }
            //有项目，有状态
            if (params.get("status") != "") {
                example.createCriteria().andEqualTo("status", params.get("status")).andIn("itemId", list1);
            } else {
                //有项目，没有状态
                example.createCriteria().andIn("itemId", list1).andNotEqualTo("status", 0);
            }

        } else {
            //没有项目
            example.createCriteria().andEqualTo("itemId", -1);
        }


        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<CheckTable> list = checkTableMapper.selectByExample(example);

        //解析分页结果
        PageInfo<CheckTable> info = new PageInfo<>(list);

        return new PageResult<>(200, info.getTotal(), list);
    }
}
