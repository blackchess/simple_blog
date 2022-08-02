package com.liaoxin.service;

import com.liaoxin.domain.Mail;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
public interface CodeService {

    /**
     * 发送验证码功能
     */
    void sendCode(Mail mail);

    /**
     * 获取验证码功能
     */
    String getCode(String mail);

}
