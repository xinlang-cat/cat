package com.xinlang.zly_xyx.cat_log.controller;


import com.xinlang.zly_xyx.cat_log.service.LogService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping("/logs-anon/internal")
	public void save(@RequestBody Log log) {
		logService.save(log);
	}


	/**
	 * 日志查询
	 * 
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('log:query')")
	@GetMapping("/logs")
	public Page<Log> findLogs(@RequestParam Map<String, Object> params) {
		return logService.findLogs(params);
	}

}
