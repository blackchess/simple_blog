package liaoxin.controller;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.exception.AppException;
import liaoxin.model.MailEntity;
import liaoxin.model.SmsEntity;
import liaoxin.service.MailService;
import liaoxin.service.MessageService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
@RestController
@RequestMapping("verification")
public class VerificationController {

    @Autowired
    MailService mailService;
    @Autowired
    MessageService messageService;

    /**
     * 发送邮件
     * @param mailEntity  邮件参数
     */
    @PostMapping("sendMail")
    public ResultBean sendSimpleMail(@RequestBody MailEntity mailEntity){
        if(StringUtils.isEmpty(mailEntity.getSendTo()) || StringUtils.isBlank(mailEntity.getSendTo())) {
            throw new AppException("邮箱发件人有误");
        }
        if(StringUtils.isEmpty(mailEntity.getKind()) || StringUtils.isBlank(mailEntity.getKind())){
            throw new AppException("邮件类型不能为空");
        }
        switch (mailEntity.getKind()){
            case "1" :
                mailService.sendSimpleMail(mailEntity);
                break;
            case "2" :
                mailService.sendHTMLMail(mailEntity);
            case "3" :
                mailService.sendAttachMail();
        }
        mailService.sendSimpleMail(mailEntity);
        return ResultBean.success("邮件已发送至邮箱，请前往查看！");
    }

    /**
     * 发送短信
     * @param smsEntity 短信参数
     */
    @PostMapping("sendPhone")
    public ResultBean sendPhoneCode(@RequestBody SmsEntity smsEntity){
        messageService.sendMessage(smsEntity);
        return ResultBean.success("注册成功！");
    }

}
