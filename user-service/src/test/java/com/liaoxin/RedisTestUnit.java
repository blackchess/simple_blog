//package com.liaoxin;
//
//import com.liaoxin.domain.Mail;
//import com.liaoxin.service.CodeService;
//import com.liaoxin.service.UserCacheService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserApplication.class)
//public class RedisTestUnit {
//
//    @Autowired
//    UserCacheService userCacheService;
//
//    @Autowired
//    CodeService codeService;
//
//    @Test
//    public void sendCodeTest(){
//        Mail mail = new Mail();
//        mail.setSendTo("287782773@qq.com");
//        //codeService.sendCode(mail);
//        codeService.sendPhoneCode("18349254737");
//    }
//}
