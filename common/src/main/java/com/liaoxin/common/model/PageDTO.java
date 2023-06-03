package com.liaoxin.common.model;

import lombok.Data;

@Data
public class PageDTO {

    //搜索条件
    private String searchWord;
    //当前页数
    private Integer pageNum;
    //本页大小
    private Integer pageSize;
}
