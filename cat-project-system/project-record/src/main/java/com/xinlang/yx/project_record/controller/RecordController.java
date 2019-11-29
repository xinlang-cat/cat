package com.xinlang.yx.project_record.controller;

import com.xinlang.yx.project_record.VO.RecordVO;
import com.xinlang.yx.project_record.bean.Record;
import com.xinlang.yx.project_record.bean.RecordResult;
import com.xinlang.yx.project_record.service.IRecordService;
import com.xinlang.yx.project_record.utils.constant;
import com.xinlang.zly_xyx.log.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 杨珣
 * @date 2019-11-12
 */
@RestController
public class RecordController {
    @Autowired
    private IRecordService recordService;

    @LogAnnotation(module = "添加实施日志")
    @PostMapping("/record")
    public Record save(@RequestBody Record Record){
        System.out.println(Record);
        recordService.insert(Record);
        return Record;
    }


    @LogAnnotation(module = "实施日志添加图片")
    @PostMapping("/record/addFile")
    public void addFile(@PathVariable List<Integer> fileIds,Integer id){
        recordService.addFile(fileIds,id);

    }

    @LogAnnotation(module = "修改实施日志")
    @PutMapping("/record")
    public Record update(@RequestBody Record Record){
        recordService.update(Record);
        return Record;
    }

    @LogAnnotation(module = "删除实施日志")
    @DeleteMapping("/record/{id}")
    public void delete(@PathVariable Integer id){
        recordService.delete(id);

    }
    @LogAnnotation(module = "删除实施日志图片")
    @DeleteMapping("/record/delFile")
    public void deleteFile(@PathVariable List<Integer> fileIds,Integer id){
        recordService.delFile(fileIds,id);

    }


    @LogAnnotation(module = "获取自己所有的实施日志")
    @GetMapping("/getMyJournal/{targetId}")
    public List<Record> findByTarIdAUID(@PathVariable Integer targetId){

        return recordService.findByTarIdAUID(targetId);//所有的
    }

    @LogAnnotation(module = "获取未提交的实施日志")
    @GetMapping("/getUnSubJournal/{targetId}")
    public List<RecordResult> findByTarIdAUIDASTU(@PathVariable Integer targetId){
        List<RecordResult> results = recordService.find(targetId,constant.ConstantStatus.UN_SUBMIT,1);
        return results;
    }


    @LogAnnotation(module = "提交实施日志")
    @PutMapping("/record/submit/{id}")
    public void submit(@PathVariable Integer id){
        System.out.println("提交了");
         recordService.check(id,constant.ConstantStatus.CHECK_IN);

    }

    @LogAnnotation(module = "获取审核中的实施日志")
    @GetMapping("/record/toCheck/{targetId}")
    public List<Record> toCheck(@PathVariable Integer targetId){

        return recordService.findByTarIdAUIDASTU(targetId,constant.ConstantStatus.CHECK_IN);
    }

    @LogAnnotation(module = "审核通过")
    @PutMapping("/record/checkPass/{id}")
    public void checkPass(@PathVariable Integer id){

        recordService.check(id,constant.ConstantStatus.CHECK_PASS);

    }

    @LogAnnotation(module = "审核不通过")
    @PutMapping("/record/checkFail/{id}")
    public void checkFail(@PathVariable Integer id){
        recordService.check(id,constant.ConstantStatus.CHECK_fail);
    }

    @LogAnnotation(module = "获取过审的实施日志")
    @GetMapping("/record/getPassRecord/{targetId}")
    public ResponseEntity<List<Record>> getPassRecord(@PathVariable Integer targetId){
        System.out.println("targetId="+targetId);
        List<Record> records=recordService.findByTarIdAUIDASTU(targetId,constant.ConstantStatus.CHECK_PASS);
        return ResponseEntity.ok(records);
    }

    @LogAnnotation(module = "获取过审的实施日志以及文件")
    @GetMapping("/record/getPassRecordVO/{targetId}")
    public List<RecordVO> getPassRecordVO(@PathVariable Integer targetId){

        return recordService.findByTarIdAUIDASTUVO(targetId,constant.ConstantStatus.CHECK_PASS);
    }

    @LogAnnotation(module = "获取实施日志以及文件")
    @GetMapping("/record/getRecordVOById/{id}")
    public RecordVO getPassRecordVOById(@PathVariable Integer id){
        return recordService.findVOById(id);
    }

    @LogAnnotation(module = "获取自己所有的实施日志")
    @GetMapping("/findByproId/{proId}")
    public List<Record> findByproId(@PathVariable Integer proId,@PathVariable Integer status,@PathVariable Integer wetherUser){

        return recordService.findByproId(proId,status,wetherUser);//所有的
    }

    @LogAnnotation(module = "获取所有的实施日志")
    @GetMapping("/record")
    public  ResponseEntity<List<RecordResult>> find(@RequestParam Map<String, Object> params){


        Integer status = Integer.valueOf ((String) params.get("status"));
        Integer proId = Integer.valueOf ((String) params.get("proId"));
        Integer weatherUser = Integer.valueOf ((String) params.get("type"));
        System.out.println(proId+","+status+","+weatherUser);
        List<RecordResult> results = recordService.find(proId,status,weatherUser);

        return ResponseEntity.ok(results);//所有的

    }

    @LogAnnotation(module = "获取通过的实施日志")
    @GetMapping("/record/findPass/{proId}")

    public List<RecordResult> findPass(@PathVariable Integer proId){
        List<RecordResult> results =recordService.findPass(proId);

        return results;//所有的

    }

    @LogAnnotation(module = "获取我的所有的实施日志")
    @GetMapping("/record/findAll/{proId}")

    public List<RecordResult> findAll(@PathVariable Integer proId){
        List<RecordResult> results =recordService.findAll(proId);

        return results;//所有的

    }

    @LogAnnotation(module = "获取我的所有的实施日志")
    @GetMapping("/record/findCheck/{proId}")

    public List<RecordResult> findCheck(@PathVariable Integer proId){
        List<RecordResult> results =recordService.findCheck(proId);

        return results;//所有的

    }






}
