package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemContentMapper;
import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.service.IItemContentService;
;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ItemContentService extends BaseService<ItemContent> implements IItemContentService {

    @Autowired
    private ItemContentMapper itemContentMapper;

    @Override
    @Transactional
    public void saveContents(List<ItemContent> itemContents) {
        //批量插入
        int count = itemContentMapper.insertList(itemContents);
        if (count < 1){
            throw new ItemException(ExceptionEnum.SAVE_ERROR);
        }
    }

    @Override
    public void deleteContentByItemId(Integer item_id) {
        ItemContent itemContent = new ItemContent();
        itemContent.setItem_id(item_id);
        itemContentMapper.delete(itemContent);
    }
}
