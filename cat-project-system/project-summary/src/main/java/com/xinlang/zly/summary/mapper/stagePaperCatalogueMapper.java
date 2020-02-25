package com.xinlang.zly.summary.mapper;

import com.xinlang.zly.summary.bean.StagePaperCatalogue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("stagePaperCatalogueMapper")
public interface stagePaperCatalogueMapper extends tk.mybatis.mapper.common.Mapper<StagePaperCatalogue> {
}
