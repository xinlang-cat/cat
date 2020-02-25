package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.StagePatentCatalogue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("stagePatentCatalogueMapper")
public interface StagePatentCatalogueMapper extends tk.mybatis.mapper.common.Mapper<StagePatentCatalogue> {
}
