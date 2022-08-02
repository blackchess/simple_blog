package com.liaoxin;

import com.liaoxin.domain.Mail;
import com.liaoxin.domain.User;
import com.liaoxin.service.CodeService;
import com.liaoxin.service.UserCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class RedisTestUnit {

    @Autowired
    UserCacheService userCacheService;

    @Autowired
    CodeService codeService;

    @Test
    public void sendCodeTest(){
        Mail mail = new Mail();
        mail.setSendTo("287782773@qq.com");
        codeService.sendCode(mail);
    }


}
