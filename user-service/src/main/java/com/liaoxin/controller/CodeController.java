package com.liaoxin.controller;

import com.alibaba.fastjson.JSON;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.utils.CodeUtils;
import com.liaoxin.common.utils.SecretUtils;
import com.liaoxin.domain.Mail;
import com.liaoxin.domain.model.QRCodeModel;
import com.liaoxin.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/

@RestController
@RequestMapping("/code")
@RefreshScope
public class CodeController {

    @Autowired
    CodeService codeService;


    /**
     * 发送邮箱验证码
     * @param mailNum 电子邮箱号
     */
    @GetMapping("")
    public ResultBean sendCode(@RequestParam("mail") String mailNum){
        Mail mail = new Mail();
        mail.setSendTo(mailNum);
        codeService.sendCode(mail);
        return ResultBean.success("发送验证码成功，请前往邮箱查看");
    }

    /**
     * 发送手机验证码
     * @param phoneNum 手机号码
     */
    @GetMapping("/phoneCode")
    public ResultBean sendMobileCode(@RequestParam("phoneNum") String phoneNum){
        codeService.sendPhoneCode(phoneNum);
        return ResultBean.success("发送验证码成功，请前往手机查看");
    }

    /**
     * 获取二维码
     */
    @GetMapping("quickResponse")
    public void sendQuickResponseCode(HttpServletResponse response) throws Exception{
        //创建二位码信息加密后返回手机端
        QRCodeModel qrCodeModel = new QRCodeModel();
        qrCodeModel.setUuid(UUID.randomUUID().toString());
        qrCodeModel.setCreateTime(LocalDateTime.now());
        qrCodeModel.setStatus(QRCodeModel.QRStatus.UNCHECK);
        String md5String = SecretUtils.MD5Encoding(JSON.toJSONString(qrCodeModel), WebConst.SECURT);
        CodeUtils.createQRCodePicture(md5String,response);
    }

}
