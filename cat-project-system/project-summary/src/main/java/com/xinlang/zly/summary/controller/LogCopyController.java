package com.xinlang.zly.summary.controller;

import com.xinlang.bean.util.LogCopy;
import com.xinlang.zly.summary.service.ILogCopyService;
import com.xinlang.zly_xyx.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class LogCopyController {

    @Autowired
    private ILogCopyService logService;
    @GetMapping("/logs")
    public Page<LogCopy> findLogs(@RequestParam Map<String, Object> params) {
        return logService.findPageByParams(params, LogCopy.class);
    }
}
