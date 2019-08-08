package com.xinlang.zly_xyx.cat_manage_backend.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_manage_backend.dao.BlackIPDao;
import com.xinlang.zly_xyx.cat_manage_backend.model.BlackIP;
import com.xinlang.zly_xyx.cat_manage_backend.service.BlackIPService;
import com.xinlang.zly_xyx.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BlackIPServiceImpl implements BlackIPService {

	@Autowired
	private BlackIPDao blackIPDao;

	@Transactional
	@Override
	public void save(BlackIP blackIP) {
		BlackIP ip = blackIPDao.findByIp(blackIP.getIp());
		if (ip != null) {
			throw new IllegalArgumentException(blackIP.getIp() + "已存在");
		}

		blackIPDao.save(blackIP);
		log.info("添加黑名单ip:{}", blackIP);
	}

	@Transactional
	@Override
	public void delete(String ip) {
		int n = blackIPDao.delete(ip);
		if (n > 0) {
			log.info("删除黑名单ip:{}", ip);
		}
	}

	@Override
	public Page<BlackIP> findBlackIPs(Map<String, Object> params) {
		int total = blackIPDao.count(params);
		List<BlackIP> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, false);

			list = blackIPDao.findData(params);
		}
		return new Page<>(total, list);
	}
}
