package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.ItemFundUse;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ItemFundUseMapper extends Mapper<ItemFundUse> {
    /**
     * 插入使用凭据关系
     * @param fund_use_id
     * @param bill_url
     */
    @Insert("INSERT INTO item_fund_use_bill (fund_use_id, bill_url) VALUES (#{fund_use_id},#{bill_url})")
    int insertUseBill(@Param("fund_use_id") Integer fund_use_id, @Param("bill_url") String bill_url);

    @Select("SELECT bill_url FROM item_fund_use_bill WHERE fund_use_id=#{id}")
    List<String> selectUseBill(Integer id);

    @Delete("DELETE FROM item_fund_use_bill WHERE fund_use_id = #{fundUseId}")
    int deleteUseBill(Integer fundUseId);
}
