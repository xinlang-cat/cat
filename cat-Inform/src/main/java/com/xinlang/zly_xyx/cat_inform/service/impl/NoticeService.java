package com.xinlang.zly_xyx.cat_inform.service.impl;

import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_inform.bean.Notice;
import com.xinlang.zly_xyx.cat_inform.service.INoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoticeService extends BaseService<Notice> implements INoticeService {
}
