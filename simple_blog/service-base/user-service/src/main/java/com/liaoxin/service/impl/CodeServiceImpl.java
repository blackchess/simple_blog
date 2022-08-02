package com.liaoxin.service.impl;

import com.liaoxin.client.VerificationClient;
import com.liaoxin.domain.Mail;
import com.liaoxin.service.CodeService;
import com.liaoxin.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    VerificationClient verificationClient;

    @Autowired
    UserCacheService userCacheService;

    @Override
    public void sendCode(Mail mail) {
        String code = Integer.toString ((int)((Math.random()*9+1) * 100000));
        userCacheService.setCode(mail.getSendTo(), code);
        mail.setText("验证码：" + code);
        mail.setSubject("Blog新用户注册验证");
        verificationClient.sendSimpleMail(mail);
    }

    @Override
    public String getCode(String mail) {
        return userCacheService.selectCode(mail);
    }
}
