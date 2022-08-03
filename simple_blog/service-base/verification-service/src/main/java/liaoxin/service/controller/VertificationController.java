package liaoxin.service.controller;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.domain.Mail;
import liaoxin.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
@RestController
@RequestMapping("verification")
public class VertificationController {

    @Autowired
    MailService mailService;

    @PostMapping("")
    public ResultBean sendSimpleMail(@RequestBody Mail mail){
        mailService.sendSimpleMail(mail);
        return ResultBean.success("注册成功！");
    }

}
