package com.xinlang.zly.label.controller;

import com.xinlang.zly.label.bean.Label;
import com.xinlang.zly.label.service.ILabelService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class LabelController {

    @Autowired
    private ILabelService labelService;

    /**
     * 添加标签
     * @param label
     * @return
     */
    @ApiOperation(value="添加标签，全参")
    @LogAnnotation(module = "添加标签")
    @PostMapping("/label")
    @PreAuthorize("hasAnyAuthority('project:label:save')")
    public Label save(@RequestBody Label label){

        labelService.save(label);
        return label;
    }

    /**
     * 修改
     * @param label
     * @return
     */
    @ApiOperation(value="根据id修改标签，sign不可以修改")
    @LogAnnotation(module = "修改标签")
    @PutMapping("/label")
    @PreAuthorize("hasAnyAuthority('project:label:update')")
    public Label update(@RequestBody Label label) {
        labelService.update(label);
        return label;
    }

    /**
     * 根据ids查询,id以逗号分隔
     * @param ids
     * @return
     */
    @ApiOperation(value="根据ids查询标签")
    @LogAnnotation(module = "根据ids查询标签")
    @GetMapping("/label/{ids}")
    public List<Label> findByIds(@PathVariable Set<Integer> ids){
        return labelService.findByIds(ids);
    }

    /**
     * 删除标签
     * @param id
     */
    @ApiOperation(value="根据id删除标签")
    @LogAnnotation(module = "删除标签")
    @DeleteMapping("/label/{id}")
    @PreAuthorize("hasAnyAuthority('project:label:delete')")
    public void delete(@PathVariable Integer id) {
        labelService.delete(id);
    }

    /**
     * 根据标识获取标签树
     * @param signs
     * @return
     */
    @ApiOperation(value="根据sign获取标签树")
    @LogAnnotation(module = "根据sign获取标签树")
    @GetMapping("/label/tree/{signs}")
    public List<Label> findTreeBySign(@PathVariable Set<String> signs) {
        List<Label>  list =  labelService.findTreeBySign(signs);
        if(list.size()>1){
            return null;
        }
       return list;
    }

    /**
     * 获取所有标签树
     * @return
     */
    @ApiOperation(value="获取所有标签树")
    @LogAnnotation(module = "获取所有标签树")
    @GetMapping("/label/tree/all")
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
     * @return
     */
    @ApiOperation(value="获取所有标签")
    @LogAnnotation(module = "获取所有标签")
    @GetMapping("/label/all")
    public List<Label> findAll() {
        return  labelService.findAll();
    }

    /**
     * 所有标签树
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
