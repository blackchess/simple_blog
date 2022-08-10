package com.liaoxin.service.impl;

import com.liaoxin.client.VerificationClient;
import com.liaoxin.common.exception.AppException;
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
        mail.setSubject("Blog用户验证");
        try {
            verificationClient.sendSimpleMail(mail);
        }catch (Exception e){
            throw new AppException(503, "验证服务器发生异常，请稍后重试");
        }
    }

    @Override
    public String getCode(String mail) {
        return userCacheService.selectCode(mail);
    }
}
