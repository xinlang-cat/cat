package com.xinlang.zly.label.controller;

import com.xinlang.zly.label.bean.Label;
import com.xinlang.zly.label.service.ILabelService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-10-31
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private ILabelService labelService;

    /**
     * 添加标签
     * @param label
     * @return
     */
    @LogAnnotation(module = "添加标签")
    @PostMapping
    public Label save(@RequestBody Label label){
        label.setCreateTime(new Date());
        labelService.save(label);
        return label;
    }

    /**
     * 修改
     * @param label
     * @return
     */
    @LogAnnotation(module = "修改标签")
    @PutMapping
    public Label update(@RequestBody Label label) {
        label.setUpdateTime(new Date());
        label.setEnabled(1);
        labelService.update(label);
        return label;
    }

    /**
     * 根据ids查询,id以逗号分隔
     * @param ids
     * @return
     */
    @LogAnnotation(module = "根据ids查询标签")
    @GetMapping("/{ids}")
    public List<Label> findByIds(@PathVariable Set<Integer> ids){
        return labelService.findByIds(ids);
    }

    /**
     * 删除标签
     * @param id
     */
    @LogAnnotation(module = "删除标签")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        labelService.delete(id);
    }

    /**
     * 根据标识获取标签树
     * @param sign
     * @return
     */
    @LogAnnotation(module = "根据标识获取标签树")
    @GetMapping("/tree/{sign}")
    public List<Label> findTreeBySign(@PathVariable String sign) {
        List<Label>  list =  labelService.findTreeBySign(sign);
        if(list.size()>1){
            return null;
        }
       return list;
    }

    /**
     * 获取所有标签树
     * @return
     */
    @LogAnnotation(module = "获取所有标签树")
    @GetMapping("/tree/all")
    public List<Label> findTreeAll() {
        List<Label> all =  labelService.findAll();
        List<Label> list = new ArrayList<>();
        for(int i=0;i<all.size();i++){
            if(all.get(i).getPid()==0){
                list.add(all.get(i));
            }
        }
        setLabelTree(all,list);
        return  list;
    }
    /**
     * 所有标签树
     *
     * @param all
     * @param list
     */
    private void setLabelTree( List<Label> all, List<Label> list) {
        list.forEach(label -> {
            List<Label> childs = new ArrayList<>();
            all.forEach(l->{
                if(l.getPid()==label.getId() && l.getEnabled() == 1){
                    childs.add(l);
                }
            });
            label.setChild(childs);
            setLabelTree(all,childs);
        });
    }



}
