package com.xinlang.zly_xyx.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * @param <T>
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -7119897356039947422L;

    private int total;
    private List<T> data;

}
