package com.xinlang.zly_xyx.cat_log.service;

import java.util.Map;

import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.Log;

public interface LogService {

	/**
	 * 保存日志
	 *
	 * @param log
	 */
	void save(Log log);

	Page<Log> findLogs(Map<String, Object> params);

}
