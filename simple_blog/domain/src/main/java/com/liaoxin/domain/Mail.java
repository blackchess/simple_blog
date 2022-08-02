package com.liaoxin.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
@Data
public class Mail {

    private String sendTo;

    private String subject;

    private String text;

    private Map<String, File> attach;

}
