package com.xinlang.zly.label.service.impl;

import com.xinlang.zly.label.bean.Label;
import com.xinlang.zly.label.mapper.LabelMapper;
import com.xinlang.zly.label.service.ILabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-10-31
 */
@Slf4j
@Service
public class LabelService implements ILabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public void save(Label label){
        findDuplicate(label);
        labelMapper.insert(label);
    }

    /**
     * 查重
     * @param label
     */
    private void findDuplicate(Label label){
        Example example = new Example(Label.class);
        example.createCriteria().andEqualTo("sign",label.getSign());
        List<Label> list = labelMapper.selectByExample(example);
        if(!list.isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void update(Label label) {
        //不允许客户端修改标识
        label.setSign(null);
        Example example = new Example(Label.class);
        example.createCriteria().andEqualTo("id",label.getId());
        labelMapper.updateByExampleSelective(label,example);
    }

    @Override
    public List<Label> findAll() {
        return labelMapper.selectAll();
    }

    @Override
    public List<Label> findByIds(Set<Integer> ids) {
        Example example = new Example(Label.class);
        example.createCriteria().andIn("id",ids);
        return labelMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(Label.class);
        example.createCriteria().andEqualTo("id",id);
        List<Label> list = labelMapper.selectByExample(example);
        findChild(list);
        deleteChild(list);
    }

    @Override
    public List<Label> findTreeBySign(String sign) {
      Example example = new Example(Label.class);
      example.createCriteria().andEqualTo("sign",sign);
      List<Label> all = labelMapper.selectByExample(example);
        findChild(all);
      return all;
    }

    /**
     * 删除子集
     * @param label
     */
    private void deleteChild(List<Label> label){
        for(int i=0;i<label.size();i++){
            List<Label> child = label.get(i).getChild();
            if(child != null){
                deleteChild(child);
            }
            labelMapper.delete(label.get(i));
        }

    }

    /**
     * 创建树
     * @param all
     */
    private void findChild(List<Label> all){
        for(int i = 0;i<all.size();i++){
            Example example = new Example(Label.class);
            example.createCriteria().andEqualTo("pid",all.get(i).getId()).andEqualTo("enabled",1);
            List<Label> child = labelMapper.selectByExample(example);
            all.get(i).setChild(child);
            findChild(child);
        }

    }

}
