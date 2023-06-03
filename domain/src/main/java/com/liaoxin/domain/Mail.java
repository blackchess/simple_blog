package com.liaoxin.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
@Data
public class Mail {
    /**
     * 接受用户
     */
    private String sendTo;
    /**
     * 主题
     */
    private String subject;
    /**
     * 信息内容
     */
    private String text;
    /**
     * 附件文件
     */
    private Map<String, File> attach;

}
