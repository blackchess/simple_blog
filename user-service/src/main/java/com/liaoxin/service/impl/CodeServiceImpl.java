package com.liaoxin.service.impl;

import com.alibaba.fastjson.JSON;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.common.utils.CodeUtils;
import com.liaoxin.domain.Mail;
import com.liaoxin.domain.SmsEntity;
import com.liaoxin.service.CodeService;
import com.liaoxin.service.UserCacheService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    UserCacheService userCacheService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendCode(Mail mail) {
        String checkCode = CodeUtils.createCode(WebConst.CHECK_CODE_LEN);
        mail.setText("验证码：" + checkCode);
        mail.setSubject("simpleBlog用户验证");
        userCacheService.setCode(mail.getSendTo(),checkCode);
        try {
            //发送信息给消息队列
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("messageExchange","mail",JSON.toJSONString(mail),correlationData);
        }catch (Exception e){
            throw new AppException(503, "验证服务器发生异常，请稍后重试");
        }
    }

    @Override
    public String getCode(String mail) {
        return userCacheService.selectCode(mail);
    }

    @Override
    public void sendPhoneCode(String phoneNum) {
        //将验证码存进用户缓存
        String code = CodeUtils.createCode(WebConst.CHECK_CODE_LEN);
        userCacheService.setCode(phoneNum,code);
        //调用发送短信接口
        Map messageMap = new HashMap();
        messageMap.put("code",code);
        SmsEntity smsEntity = new SmsEntity(phoneNum,JSON.toJSONString(messageMap));
        try {
            //发送信息给消息队列
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("messageExchange","phone",JSON.toJSONString(smsEntity),correlationData);
        }catch (Exception e){
            throw new AppException(503, "验证服务器发生异常，请稍后重试");
        }
    }
}
