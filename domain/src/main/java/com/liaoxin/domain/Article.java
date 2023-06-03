package com.liaoxin.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章类
 */
@Data
@TableName("cms_article")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    @ExcelProperty("序号")
    private Long userId;
    @ExcelProperty("标题")
    private String title;
    @ExcelProperty("内容")
    private String content;
    @ExcelProperty("浏览数")
    private Integer viewNum;
    @ExcelProperty("评论数")
    private Integer commentNum;
    @ExcelProperty("状态")
    private Integer status;
    @ExcelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ExcelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;




}