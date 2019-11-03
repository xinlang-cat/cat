package com.xinlang.zly.label.service;



import com.xinlang.zly.label.bean.Label;

import java.util.List;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-10-31
 */
public interface ILabelService {
    /**
     * 添加
     */
    void save(Label label);

    /**
     * 修改
     */
    void update(Label label);

    /**
     * 查询所有
     */
    List<Label> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    List<Label> findByIds(Set<Integer> id);

    /**
     * 删除
     */
    void delete(Integer id);

    /**
     * 根据标识查询
     * @param sign
     * @return
     */
    List<Label> findTreeBySign(String sign);
}
