package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemPersonnelViceMapper;
import com.xinlang.cat_project.item.pojo.ItemPersonnelVice;
import com.xinlang.cat_project.item.service.IItemPersonnelViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemPersonnelViceService extends BaseService<ItemPersonnelVice> implements IItemPersonnelViceService {
    @Autowired
    private ItemPersonnelViceMapper itemPersonnelMapper;

    @Override
    public void savePersonnels(List<ItemPersonnelVice> itemPersonnels) {
        int i = itemPersonnelMapper.insertList(itemPersonnels);
    }

    @Override
    public void deleteByAttribute(ItemPersonnelVice itemPersonnel) {
        int i = itemPersonnelMapper.delete(itemPersonnel);
    }
}
