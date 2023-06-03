package com.liaoxin.common.common;

import lombok.Data;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/27
 **/

@Data
public class PageBean<T>  {

    //当前页
    private Integer current;
    //每页条数
    private Integer limit;
    //总数
    private Integer total;
    //数据
    private T list;

    public PageBean() {
    }

    public PageBean(T list) {
        this.list = list;
    }

    public PageBean(Integer current, Integer limit, T list) {
        this.current = current;
        this.limit = limit;
        this.list = list;
    }

    public PageBean(Integer current, Integer limit, Integer total, T list) {
        this.current = current;
        this.limit = limit;
        this.total = total;
        this.list = list;
    }
}
