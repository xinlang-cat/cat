package com.xinlang.yx.project_report.controller;

import com.xinlang.yx.project_report.bean.ReportFile;
import com.xinlang.yx.project_report.service.IReportFileService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@RestController
@RequestMapping("/reportFile")
public class ReportFileController {
    @Autowired
    private IReportFileService fileService;

    @LogAnnotation(module="添加阶段报告文件")
    @PostMapping
    public void save(@RequestParam("File") MultipartFile multipartFile, Integer reportId){
        ReportFile reportFile = new ReportFile();
        reportFile.setReportId(reportId);
        fileService.uploadFile(multipartFile,reportFile);

    }

    @LogAnnotation(module = "查看阶段报告文件")
    @GetMapping("/getReFiles/{reportId}")
    public List<ReportFile> findByProId(@PathVariable Integer reportId){
        return fileService.findByReportId(reportId);
    }

    @LogAnnotation(module = "删除阶段报告文件")
    @DeleteMapping("/delRefiles/{ids}")
    public void delete(@PathVariable Set<Integer> ids){
        fileService.deleteByIds(ids);
    }


}
