package com.xinlang.zly_xyx.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {


    private static final long serialVersionUID = 2749882542068799324L;
    private int total;
    private List<T> data;

}
