package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author 梁应昌
 * 2019/9/27
 */
@Data
@ToString
public class PageResult<T> {
    private Integer statusCode; //状态码
    private Long total;// 总条数
    private Long totalPage;// 总页数
    private List<T> data;// 当前页数据

    public PageResult() {
    }

    public PageResult(Integer statusCode, Long total, List<T> data) {
        this.statusCode = statusCode;
        this.total = total;
        this.data = data;
    }

    public PageResult(Integer statusCode, Long total, Long totalPage, List<T> data) {
        this.statusCode = statusCode;
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

}