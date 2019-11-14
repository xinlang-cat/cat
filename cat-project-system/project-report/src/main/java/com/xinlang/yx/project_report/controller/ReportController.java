package com.xinlang.yx.project_report.controller;

import com.xinlang.yx.project_report.bean.Report;
import com.xinlang.yx.project_report.service.IReportService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private IReportService reportService;

    @LogAnnotation(module="添加阶段报告")
    @PostMapping
    public Report save(@RequestBody Report report){

        reportService.save(report);
        return report;
    }

    @LogAnnotation(module = "修改阶段报告")
    @PutMapping
    public Report update(@RequestBody Report report){
        reportService.update(report);
        return report;
    }

    @LogAnnotation(module = "删除阶段报告")
    @DeleteMapping("/del/{id}")
    public void delById(@PathVariable Integer id){
        reportService.delete(id);
    }

    @LogAnnotation(module = "获取所有阶段报告")
    @GetMapping("/getAllReport/{projectId}")
    public List<Report> findByProId(@PathVariable Integer projectId){
        return reportService.findByProId(projectId);
    }

    @LogAnnotation(module = "获取阶段报告")
    @GetMapping("/getReport/{id}")
    public Report findById(@PathVariable Integer id){
        return reportService.find(id);
    }

   /* @LogAnnotation(module = "获取阶段报告以及相关文件")
    @GetMapping("/getReportVO/{id}")
    public ReportVO findVOById(@PathVariable Integer id){
        return reportService.findVO(id);
    }

    @LogAnnotation(module = "获取所有阶段报告以及相关文件")
    @GetMapping("/getAllReportAndFile/{projectId}")
    public List<ReportVO> findVOByProId(@PathVariable Integer projectId){
        return reportService.findVOByProId(projectId);
    }*/

}
