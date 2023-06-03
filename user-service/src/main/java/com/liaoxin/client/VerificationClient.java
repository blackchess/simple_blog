package com.liaoxin.client;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Mail;
import com.liaoxin.domain.SmsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
@Component
@FeignClient(name = "verification-service")
public interface VerificationClient {

    @PostMapping("/verification")
    ResultBean sendSimpleMail(@RequestBody Mail mail);

    @PostMapping("/Phoneverification")
    ResultBean sendSimplePhoneCode(@RequestBody SmsEntity smsEntity);



}
