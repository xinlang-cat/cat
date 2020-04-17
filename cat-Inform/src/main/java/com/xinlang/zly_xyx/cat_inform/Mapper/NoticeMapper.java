package com.xinlang.zly_xyx.cat_inform.Mapper;

import com.xinlang.zly_xyx.cat_inform.bean.Notice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
@Repository("noticeMapper")
public interface NoticeMapper extends Mapper<Notice> {
}
