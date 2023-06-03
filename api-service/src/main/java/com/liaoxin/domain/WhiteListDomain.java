package com.liaoxin.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2023/5/16
 * @白名单配置类
 **/
@Component
@ConfigurationProperties("request")
public class WhiteListDomain {

    /**
     * 白名单列表
     */
    List<String> whiteList;

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}