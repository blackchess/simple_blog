package com.liaoxin.controller;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Mail;
import com.liaoxin.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    CodeService codeService;

    @GetMapping("")
    public ResultBean sendCode(@RequestParam("mail") String mailNum){
        Mail mail = new Mail();
        mail.setSendTo(mailNum);
        codeService.sendCode(mail);
        return ResultBean.success("发送验证码成功，请前往邮箱查看");
    }

}
