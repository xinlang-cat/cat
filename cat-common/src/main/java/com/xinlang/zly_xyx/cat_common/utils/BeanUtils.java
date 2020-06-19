package com.xinlang.zly_xyx.cat_common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-24
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    public BeanUtils() {
    }

    /**
     * 实例化对象：传入类对类进行实例化对象
     *
     * @param clazz 类名
     * @return 对象
     * @author Lius
     * @date 2018/10/26 13:49
     */
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

    /**
     * 实例化对象，传入类名对该类进行实例化对象
     *
     * @param clazzStr 类名,传入是必须传入全路径，com...
     * @return 对象
     * @author Lius
     * @date 2018/10/26 13:54
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 把对象封装成Map形式
     *
     * @param src 需要封装的实体对象
     * @author Lius
     * @date 2018/10/26 14:08
     */
    public static Map toMap(Object src) {
        return BeanMap.create(src);
    }

    /**
     * 把Map转换成bean对象
     *
     * @author Lius
     * @date 2018/10/26 14:09
     */
    public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
        // 对象实例化
        T bean = BeanUtils.newInstance(valueType);
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(valueType);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String properName = propertyDescriptor.getName();
            Class<?> properType = propertyDescriptor.getPropertyType();

            if ("class".equals(properName)) {
                continue;
            }
            if (beanMap.containsKey(properName)) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                Object value = beanMap.get(properName);
                if (null == writeMethod || StringUtils.isBlank(value.toString())) {
                    continue;
                }
                if(Integer.class.equals(properType)){
                    value = Integer.valueOf(value.toString());
                }
                if (!writeMethod.isAccessible()) {
                    writeMethod.setAccessible(true);
                }
                properType.cast(value);
                try {
                    writeMethod.invoke(bean, properType.cast(value));
                } catch (Throwable throwable) {
                    throw new RuntimeException("Could not set property '" + properName + " ' to bean" + throwable);
                }
            }
        }
        return bean;
    }
}
