package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeUser;
import com.xinlang.cat_project.item.mapper.ItemFundUseMapper;
import com.xinlang.cat_project.item.pojo.ItemFundUse;
import com.xinlang.cat_project.item.service.IItemFundUseService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemFundUseService extends BaseService<ItemFundUse> implements IItemFundUseService {

    @Autowired
    private ItemFundUseMapper itemFundUseMapper;
    @Autowired
    private ConsumeUser consumeUser;


    @Override
    public void saveBill(Integer id, String bill) {
        itemFundUseMapper.insertUseBill(id, bill);
    }

    @Override
    public List<String> findBill(Integer id) {
        return itemFundUseMapper.selectUseBill(id);
    }

    @Override
    @Transactional
    public void updateBill(Integer id, String bill) {
        itemFundUseMapper.deleteUseBill(id);
        itemFundUseMapper.insertUseBill(id, bill);
    }

    @Override
    public void deleteBill(Integer id) {
        itemFundUseMapper.deleteUseBill(id);
    }
}
