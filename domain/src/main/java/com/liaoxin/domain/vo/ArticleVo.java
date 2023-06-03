package com.liaoxin.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liaoxin.domain.ArticleImage;
import com.liaoxin.domain.Label;
import com.liaoxin.domain.UmsUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章返回数据对象
 */
@Data
public class ArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String title;
    @ExcelProperty("内容")
    private String content;
    @ExcelProperty("关注数")
    private Integer viewNum;
    @ExcelProperty("评论数")
    private Integer commentNum;
    @ExcelProperty("状态")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("更新时间")
    private Date updateTime;

    private UmsUser umsUser;

    private List<ArticleImage> imageList;

    private List<Label> labelList;
}
