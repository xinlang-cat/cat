package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemPersonnelMapper;
import com.xinlang.bean.projectInfo.ItemPersonnel;
import com.xinlang.cat_project.item.service.IItemPersonnelService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemPersonnelService extends BaseService<ItemPersonnel> implements IItemPersonnelService {
    @Autowired
    private ItemPersonnelMapper itemPersonnelMapper;

    @Override
    public void savePersonnels(List<ItemPersonnel> itemPersonnels) {
        int i = itemPersonnelMapper.insertList(itemPersonnels);
    }

    @Override
    public void deleteByAttribute(ItemPersonnel itemPersonnel) {
        int i = itemPersonnelMapper.delete(itemPersonnel);
    }
}
