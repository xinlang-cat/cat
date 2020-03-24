package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.ItemInformationMapper;
import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.ItemInformation;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.service.IItemInformationService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemInformationService extends BaseService<ItemInformation> implements IItemInformationService {

    @Autowired
    private ItemInformationMapper itemInformationMapper;

    @Override
    public PageResult<ItemInformation> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(ItemInformation.class);

        if (params.get("name") != "" && params.get("type") != "") {
            example.createCriteria().andLike("name", "%" + params.get("name") + "%")
                                    .andEqualTo("type", params.get("type"))
                                    .andEqualTo("edit_user_id", params.get("userId"))
                                    .andBetween("status", params.get("status1"), params.get("status2"));
        } else if (params.get("name") != "") {
            example.createCriteria().andLike("name", "%" + params.get("name") + "%")
                                    .andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        } else if (params.get("type") != "") {
            example.createCriteria().andEqualTo("type", params.get("type"))
                                    .andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        } else {
            example.createCriteria().andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<ItemInformation> list = itemInformationMapper.selectByExample(example);
        //解析分页结果
        PageInfo<ItemInformation> info = new PageInfo<>(list);
        return new PageResult<>(200, info.getTotal(), list);
    }
}
