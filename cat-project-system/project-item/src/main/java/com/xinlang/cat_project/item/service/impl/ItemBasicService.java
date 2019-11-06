package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeUser;
import com.xinlang.cat_project.item.mapper.ItemBasicMapper;
import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.service.IItemBasicService;
import com.xinlang.cat_project.item.utils.constant;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import com.xinlang.zly_xyx.user.LoginAppUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 梁应昌
 * 2019/9/27
 */
@Service
@Slf4j
public class ItemBasicService implements IItemBasicService {

    @Autowired
    private ItemBasicMapper itemBasicMapper;

    @Autowired
    private ConsumeUser consumeUser;

    @Override
    public PageResult<ItemBasic> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) throws ItemException{
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(ItemBasic.class);
        if (params!=null) {
            if(params.get("item_name") != "" && params.get("status") != ""){
                example.createCriteria().andLike("item_name", "%" + params.get("item_name") + "%").andEqualTo("status", params.get("status"));
            }else if(params.get("item_name") != ""){
                example.createCriteria().andLike("item_name", "%" + params.get("item_name") + "%");
            }else if(params.get("status") != ""){
                example.createCriteria().andEqualTo("status", params.get("status"));
            }
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<ItemBasic> list = itemBasicMapper.selectByExample(example);
        for (int i=0;i<list.size();i++){
            ItemBasic it = list.get(i);
            it.setStart_dateStr(DateUtils.dateToString(it.getStart_date(),"yyyy年MM月dd日"));
            it.setEnd_dateStr(DateUtils.dateToString(it.getEnd_date(),"yyyy年MM月dd日"));
        }
       /* if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.ITEM_NOT_FOUND);
        }*/
        //解析分页结果
        PageInfo<ItemBasic> info = new PageInfo<>(list);
        return  new PageResult<>(info.getTotal(), list);
    }

    @Override
    public void saveItem(ItemBasic basic) {

        //获取当前用户
        LoginAppUser loginAppUser = consumeUser.getLoginAppUser();
        //LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        //SET 创建人id、创建时间、状态
        basic.setEdit_userid(loginAppUser.getId().intValue());
        basic.setEdit_date(new Date());
        try {
            basic.setStart_date(DateUtils.stringToDate(basic.getStart_dateStr(), "yyyy年MM月dd日"));
            basic.setEnd_date(DateUtils.stringToDate(basic.getEnd_dateStr(), "yyyy年MM月dd日"));
        }catch (Exception e){
            log.error("日期格式错误！",e);
            throw new ItemException(ExceptionEnum.DATE_FORMAT_ERROR);
        }
        //新增项目
        int count = itemBasicMapper.insertSelective(basic);
        if(count != 1){
            throw new ItemException(ExceptionEnum.ITEM_SAVE_ERROR);
        }
    }

    @Override
    public ItemBasic queryItemById(Integer id) {
        ItemBasic basic = itemBasicMapper.selectByPrimaryKey(id);
        if(basic==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        basic.setStart_dateStr(DateUtils.dateToString(basic.getStart_date(), "yyyy年MM月dd日"));
        basic.setEnd_dateStr(DateUtils.dateToString(basic.getEnd_date(), "yyyy年MM月dd日"));
        basic.setEdit_dateStr(DateUtils.dateToString(basic.getEdit_date(), "yyyy年MM月dd日"));
        return basic;
    }

    @Override
    public void updateItem(ItemBasic basic) {
        try {
            basic.setStart_date(DateUtils.stringToDate(basic.getStart_dateStr(), "yyyy年MM月dd日"));
            basic.setEnd_date(DateUtils.stringToDate(basic.getEnd_dateStr(), "yyyy年MM月dd日"));
        }catch (Exception e){
            log.error("日期格式错误！",e);
            throw new ItemException(ExceptionEnum.DATE_FORMAT_ERROR);
        }
        int i = itemBasicMapper.updateByPrimaryKeySelective(basic);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteItem(Integer id) {
        int i = itemBasicMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_DELETE_ERROR);
        }
    }

    @Override
    public void discardItem(Integer id) {
        ItemBasic itemBasic = new ItemBasic();
        itemBasic.setId(id);
        itemBasic.setStatus(constant.ItemStatus.DISCARD);
        int i = itemBasicMapper.updateByPrimaryKeySelective(itemBasic);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_DELETE_ERROR);
        }
    }
}
