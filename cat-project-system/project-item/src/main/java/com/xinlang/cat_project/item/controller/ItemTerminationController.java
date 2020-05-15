package com.xinlang.cat_project.item.controller;

import com.xinlang.bean.util.PageResult;
import com.xinlang.bean.projectInfo.ItemTermination;
import com.xinlang.cat_project.item.service.IItemTerminationService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item/termination")
public class ItemTerminationController {
    @Autowired
    private IItemTerminationService itemTerminationService;

    @GetMapping("/page")
    @LogAnnotation(module = "查询结题申请表分页")
    @ApiOperation(value = "查询结题申请表分页")
    public ResponseEntity<PageResult<ItemTermination>> findPageByParamsVice(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                            @RequestParam(required = false) Map<String, Object> params){




        PageResult<ItemTermination> result = itemTerminationService.queryList(page, rows, sortBy, desc, params);
        return ResponseEntity.ok(result);

    }

    @ApiOperation(value = "添加终止申请")
    @LogAnnotation(module = "添加终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemTermination> save(@RequestBody ItemTermination itemTermination) {
        itemTerminationService.save(itemTermination);
        return  ResponseEntity.status(HttpStatus.CREATED).body(itemTermination);
    }

    @ApiOperation(value = "查询终止申请")
    @LogAnnotation(module = "查询终止申请")
    @GetMapping("list")
    public ResponseEntity<List<ItemTermination>> findListByParams(@RequestParam Map<String, Object> params){
        List<ItemTermination> itemTermination = itemTerminationService.findListByParams(params,ItemTermination.class);
        return ResponseEntity.ok(itemTermination);
    }

    @ApiOperation(value = "修改终止申请")
    @LogAnnotation(module = "修改终止申请")
    //@PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ItemTermination itemTermination){
        itemTerminationService.update(itemTermination);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除终止申请")
    @LogAnnotation(module = "删除终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        itemTerminationService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "通过属性删除终止申请")
    @LogAnnotation(module = "通过属性删除终止申请")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping
    public ResponseEntity<Void> deleteByAttribute(@RequestBody ItemTermination itemTermination){
        itemTerminationService.deleteByAttribute(itemTermination);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
