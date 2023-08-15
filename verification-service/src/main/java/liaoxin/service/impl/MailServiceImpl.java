package liaoxin.service.impl;

import com.alibaba.fastjson.JSON;
import liaoxin.model.MailEntity;
import liaoxin.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Value("${spring.mail.sendfrom}")
    private String sendFrom;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(MailEntity mailEntity) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendFrom);
        mailMessage.setTo(mailEntity.getSendTo());
        mailMessage.setSubject(mailEntity.getSubject());
        mailMessage.setText(mailEntity.getText());
        mailMessage.setSentDate(new Date());
        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendHTMLMail(MailEntity mailEntity) {

    }

    @Override
    public void sendAttachMail(MailEntity mailEntity) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setFrom(sendFrom);
            messageHelper.setTo(mailEntity.getSendTo());
            messageHelper.setSubject(mailEntity.getSubject());
            messageHelper.setText(mailEntity.getText());
            for(String key : mailEntity.getAttach().keySet()){
                messageHelper.addAttachment(key, mailEntity.getAttach().get(key));
            }
            messageHelper.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mailMessage);
    }

    @RabbitHandler
    @RabbitListener(queues = "mailQueue")
    private void fetchMailFromQueue(String message){
        MailEntity mailEntity = JSON.parseObject(message, MailEntity.class);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendFrom);
        mailMessage.setTo(mailEntity.getSendTo());
        mailMessage.setSubject(mailEntity.getSubject());
        mailMessage.setText(mailEntity.getText());
        mailMessage.setSentDate(new Date());
        javaMailSender.send(mailMessage);
    }

}
