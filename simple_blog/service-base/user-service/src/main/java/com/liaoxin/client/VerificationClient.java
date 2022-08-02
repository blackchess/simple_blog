package com.liaoxin.client;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Mail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
@Component
@FeignClient(name = "verification-service")
public interface VerificationClient {

    @PostMapping("/verification")
    ResultBean sendSimpleMail(@RequestBody Mail mail);



}
