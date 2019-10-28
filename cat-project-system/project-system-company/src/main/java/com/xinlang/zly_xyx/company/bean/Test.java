package com.xinlang.zly_xyx.company.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Table;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@Data
@Table(name = "test")
public class Test {

        @KeySql(useGeneratedKeys = true)
        private Integer id;
}
