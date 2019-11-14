package com.xinlang.yx.project_record.VO;

import com.xinlang.yx.project_record.bean.RecordFile;
import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import lombok.Data;


import java.util.Date;
import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Data
public class RecordVO {
    private Integer id;

    /**
     * 指标id
     */
    private Integer targetId;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 审核人id
     */
    private Long checkUserId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 实施时间
     */
    private Date workingDay;
    /**
     * 审核人
     */
    private Long createUserId;
    /**
     * 备注
     */
    private String remark;

    private List<Album> files;
}
