package com.xinlang.zly_xyx.cat_common.service.impl;

import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.cat_common.utils.BeanUtils;
import com.xinlang.zly_xyx.cat_common.utils.ExampleUtil;
import com.xinlang.zly_xyx.cat_common.utils.RowBoundsUtil;
import com.xinlang.zly_xyx.common.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-03
 */
public class BaseService<T> implements IBaseService<T> {
    @Autowired
    private Mapper<T> mapper;

    @Override
    public void save(T t) {
        mapper.insertSelective(t);
    }

    @Override
    public void update(T t) {
        mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public List<T> findListByParams(Map<String, Object> params,Class<T> beanClazz) {
        String previous = (String)params.get("previous");
        String behind = (String)params.get("behind");
        Example example = ExampleUtil.getLinkExample(params,beanClazz,previous,behind);
        return mapper.selectByExample(example);
    }

    @Override
    public Page<T> findPageByParams(Map<String, Object> params,Class<T> beanClazz) {
        String previous = (String)params.get("previous");
        String behind = (String)params.get("behind");
        List<T> list = Collections.emptyList();
        int total = 0;
        if(!StringUtils.isEmpty(previous) || !StringUtils.isEmpty(behind) ){
            Example example = ExampleUtil.getLinkExample(params,beanClazz,previous,behind);
            total =  mapper.selectCountByExample(example);
            if(total>0){
                RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
                list = mapper.selectByExampleAndRowBounds(example,rowBounds);
            }
        }else{
            T t = BeanUtils.toBean(params,beanClazz );
            total = mapper.selectCount(t);
            if(total>0){
                RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
                list = mapper.selectByRowBounds(t,rowBounds);
            }
        }
        return new Page<>(total,list);
    }

    @Override
    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
            mapper.deleteByPrimaryKey(id);
    }

}
