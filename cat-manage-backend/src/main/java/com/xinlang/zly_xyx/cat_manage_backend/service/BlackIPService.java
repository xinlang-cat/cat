package com.xinlang.zly_xyx.cat_manage_backend.service;

import java.util.Map;


import com.xinlang.zly_xyx.cat_manage_backend.model.BlackIP;
import com.xinlang.zly_xyx.common.Page;

public interface BlackIPService {

	void save(BlackIP blackIP);

	void delete(String ip);

	Page<BlackIP> findBlackIPs(Map<String, Object> params);

}
