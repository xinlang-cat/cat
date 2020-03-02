package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.bean.company.Company;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeCompany;
import com.xinlang.cat_project.item.fegin.ConsumeUser;
import com.xinlang.cat_project.item.mapper.ItemBasicMapper;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.service.IItemBasicService;
import com.xinlang.cat_project.item.utils.constant;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import com.xinlang.zly_xyx.user.LoginAppUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 梁应昌
 * 2019/9/27
 */
@Service
@Slf4j
public class ItemBasicService extends BaseService<ItemBasic> implements IItemBasicService {

    @Autowired
    private ItemBasicMapper itemBasicMapper;

    @Autowired
    private ItemUserMapper itemUserMapper;

    @Autowired
    private ConsumeUser consumeUser;

    @Autowired
    private ConsumeCompany consumeCompany;

    @Override
    public PageResult<ItemBasic> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) throws ItemException{
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(ItemBasic.class);

        if (params.get("item_name")!=null && params.get("category") != null && params.get("status") != null) {


            if(params.get("item_name") !="" && params.get("category") != ""&& params.get("status") != ""){
                example.createCriteria().andLike("item_name", "%" + params.get("item_name") + "%").andEqualTo("status", params.get("status")).andEqualTo("category", params.get("category"));
            }else if(params.get("item_name")!=""&& params.get("status") != ""){
                example.createCriteria().andLike("item_name", "%" + params.get("item_name") + "%").andEqualTo("status", params.get("status"));
            }else if(params.get("status") != "" && params.get("category") != ""){
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("category", params.get("category"));
            }else if(params.get("item_name")!=""&& params.get("category") != ""){
                example.createCriteria().andLike("item_name", "%" + params.get("item_name") + "%").andEqualTo("category", params.get("category"));
            }else if (params.get("item_name")!=""){
                example.createCriteria().andLike("item_name", "%" + params.get("item_name") + "%");
            }else if (params.get("category") != ""){
                example.createCriteria().andEqualTo("category", params.get("category"));
            }else if (params.get("status") != ""){
                example.createCriteria().andEqualTo("status", params.get("status"));
            }
            //example.createCriteria().andGreaterThanOrEqualTo("status", constant.ItemStatus.PROCEED);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<ItemBasic> list = itemBasicMapper.selectByExample(example);
        //解析分页结果
        PageInfo<ItemBasic> info = new PageInfo<>(list);
        return  new PageResult<>(200, info.getTotal(), list);
    }

    @Override
    public List<ItemBasic> queryCompanyItem() {
        //用于保存项目信息
        List<ItemBasic> list = new ArrayList<>();
        //先获取当前登录用户ID
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        //通过用户ID获取用户相关的项目ID
        ItemUser itemUser = new ItemUser();
        itemUser.setUser_id(userId);
        List<ItemUser> itemUsers = itemUserMapper.select(itemUser);
        if(!CollectionUtils.isEmpty(itemUsers)){
            //不为空，则说明在项目中属于承担方的成员
            //遍历项目ID查询项目
            for (ItemUser i: itemUsers) {
                ItemBasic itemBasic = itemBasicMapper.selectByPrimaryKey(i.getItem_id());
                list.add(itemBasic);
            }
        }else {
            //为空，则判是否是委托方、承担方、监管方
            //通过用户ID获取用户的公司信息
            Company company = consumeCompany.findByUserId(userId);
            //通过公司代码查询项目
            Example example = new Example(ItemBasic.class);
            example.createCriteria().andEqualTo("consignor",company.getDeptCode())
                    .orEqualTo("undertaker",company.getDeptCode()).orEqualTo("administrator",company.getDeptCode());
            /*List<ItemBasic> items = itemBasicMapper.selectByExample(example);*/
            list = itemBasicMapper.selectByExample(example);
            //获取进行中的项目
            /*for (ItemBasic item : items) {
                if(item.getStatus()>= constant.ItemStatus.BASICS_CHECK){
                    list.add(item);
                }
            }*/
        }
        return list;
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

    @Override
    public List<ItemBasic> findByName(String name) {
        Example example = new Example(ItemBasic.class);

        example.createCriteria().andLike("item_name",name);

        List<ItemBasic> list = itemBasicMapper.selectByExample(example);
        return list;
    }

}
