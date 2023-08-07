package com.liaoxin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/28
 **/
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfig {
    //存储地址
    private String saveUrl;
    //存储模式
    private String saveMode;

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public String getSaveMode() {
        return saveMode;
    }

    public void setSaveMode(String saveMode) {
        this.saveMode = saveMode;
    }
}
