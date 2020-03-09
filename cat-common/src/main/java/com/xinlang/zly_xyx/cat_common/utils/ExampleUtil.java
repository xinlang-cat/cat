package com.xinlang.zly_xyx.cat_common.utils;

import tk.mybatis.mapper.entity.Example;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-24
 */
public class ExampleUtil {

    public static Example getLinkExample(Map<String, Object> beanMap, Class<?> valueType,String previous,String behind){
        Example example = new Example(valueType);
        Example.Criteria criteria = example.createCriteria();
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(valueType);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String properName = propertyDescriptor.getName();
            // 过滤class属性
            if ("class".equals(properName)) {
                continue;
            }
            if (beanMap.containsKey(properName)) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (null == writeMethod) {
                    continue;
                }
                Object value = beanMap.get(properName);
                if(value != null){
                    criteria.andLike(properName,previous + value + behind);
                }

            }
        }
        return example;
    }
}
