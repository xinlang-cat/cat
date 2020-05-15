package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.mapper.ItemTerminationMapper;
import com.xinlang.bean.projectInfo.ItemInformation;
import com.xinlang.bean.projectInfo.ItemTermination;
import com.xinlang.cat_project.item.service.IItemInformationService;
import com.xinlang.cat_project.item.service.IItemTerminationService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemTerminationService extends BaseService<ItemTermination> implements IItemTerminationService {
    @Autowired
    private ItemTerminationMapper itemTerminationMapper;

    @Autowired
    private IItemInformationService itemInformationService;

    @Override
    public void deleteByAttribute(ItemTermination itemTermination) {
        int i = itemTerminationMapper.delete(itemTermination);
    }

    @Override
    public PageResult<ItemTermination> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(ItemTermination.class);
        List<ItemInformation> informations = itemInformationService.queryMyItem();
        List itemIds = new ArrayList();
        for (ItemInformation information : informations) {
            itemIds.add(information.getId());
        }
        if(itemIds.size()!=0){
            example.createCriteria().andIn("item_id", itemIds).andGreaterThanOrEqualTo("status",params.get("status"));
        }else {
            example.createCriteria().andEqualTo("item_id",-1);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<ItemTermination> list = itemTerminationMapper.selectByExample(example);
        //解析分页结果
        PageInfo<ItemTermination> info = new PageInfo<>(list);
        return new PageResult<>(200, info.getTotal(), list);
    }

}
