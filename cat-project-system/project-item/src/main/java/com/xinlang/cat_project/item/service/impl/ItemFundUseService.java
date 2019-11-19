package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeUser;
import com.xinlang.cat_project.item.mapper.ItemFundUseMapper;
import com.xinlang.cat_project.item.pojo.ItemFundUse;
import com.xinlang.cat_project.item.service.IItemFundUseService;
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
public class ItemFundUseService implements IItemFundUseService {

    @Autowired
    private ItemFundUseMapper itemFundUseMapper;
    @Autowired
    private ConsumeUser consumeUser;

    @Override
    @Transactional
    public void saveFundUse(ItemFundUse itemFundUse) {
        //设置编辑信息并添加
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        System.err.println(loginAppUser);
        itemFundUse.setEdit_userid(loginAppUser.getId().intValue());
        itemFundUse.setEdit_date(new Date());
        int i = itemFundUseMapper.insertSelective(itemFundUse);
        if(i != 1){
            throw new ItemException(ExceptionEnum.SAVE_ERROR);
        }
        //保存相关图片信息
        List<String> urls = itemFundUse.getBill_url();
        for (String url : urls) {
            int j = itemFundUseMapper.insertUseBill(itemFundUse.getId(),url);
            if(j != 1){
                throw new ItemException(ExceptionEnum.SAVE_ERROR);
            }
        }
    }

    @Override
    @Transactional
    public ItemFundUse queryFundUseById(Integer id) {
        ItemFundUse itemFundUse = itemFundUseMapper.selectByPrimaryKey(id);
        if(itemFundUse==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        itemFundUse.setEdit_dateStr(DateUtils.dateToString(itemFundUse.getEdit_date(), "yyyy年MM月dd日"));
        if(itemFundUse.getCheck_date()!=null){
            itemFundUse.setCheck_dateStr(DateUtils.dateToString(itemFundUse.getCheck_date(), "yyyy年MM月dd日"));
        }
        //查询相关图片
        List<String> urls = itemFundUseMapper.selectUseBill(itemFundUse.getId());
        itemFundUse.setBill_url(urls);
        return itemFundUse;
    }

    @Override
    @Transactional
    public List<ItemFundUse> queryFundUseByBId(Integer Bid) {
        ItemFundUse itemFundUse = new ItemFundUse();
        itemFundUse.setBudget_id(Bid);
        List<ItemFundUse> itemFundUses = itemFundUseMapper.select(itemFundUse);
        //查询相关图片
        for (ItemFundUse fundUs : itemFundUses) {
            List<String> urls = itemFundUseMapper.selectUseBill(fundUs.getId());
            fundUs.setBill_url(urls);
        }
        return itemFundUses;
    }

    @Override
    @Transactional
    public List<ItemFundUse> queryFundUseByItemIdAndUserId(Integer iId, int userTd) {

        ItemFundUse itemFundUse = new ItemFundUse();
        itemFundUse.setItem_id(iId);
        itemFundUse.setEdit_userid(userTd);
        List<ItemFundUse> itemFundUses = itemFundUseMapper.select(itemFundUse);
        //查询相关图片
        for (ItemFundUse fundUs : itemFundUses) {
            List<String> urls = itemFundUseMapper.selectUseBill(fundUs.getId());
            fundUs.setBill_url(urls);
        }
        return itemFundUses;
    }

    @Override
    @Transactional
    public void updateFundUse(ItemFundUse itemFundUse) {
        //设置编辑信息并添加
        //LoginAppUser loginAppUser = consumeUser.getLoginAppUser();
        int userTd = AppUserUtil.getLoginAppUser().getId().intValue();
        itemFundUse.setEdit_userid(userTd);
        itemFundUse.setEdit_date(new Date());
        int i = itemFundUseMapper.updateByPrimaryKeySelective(itemFundUse);
        if(i != 1){
            throw new ItemException(ExceptionEnum.UPDATE_ERROR);
        }
        //先删除相关图片信息
        itemFundUseMapper.deleteUseBill(itemFundUse.getId());
        //重新添加
        List<String> urls = itemFundUse.getBill_url();
        for (String url : urls) {
            int j = itemFundUseMapper.insertUseBill(itemFundUse.getId(),url);
            if(j != 1){
                throw new ItemException(ExceptionEnum.UPDATE_ERROR);
            }
        }
    }

    @Override
    @Transactional
    public void deleteFundUse(Integer id) {
        int i = itemFundUseMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.DELETE_ERROR);
        }
        int j =itemFundUseMapper.deleteUseBill(id);
    }
}
