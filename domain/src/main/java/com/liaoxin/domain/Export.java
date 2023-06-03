package com.liaoxin.domain;

import lombok.Data;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/30
 **/

@Data
public class Export {

    private String filename;

    private List<List<String>> headList;

    private List<String> headDescList;

    private List dataList;
}
