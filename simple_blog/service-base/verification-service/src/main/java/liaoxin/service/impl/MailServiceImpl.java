package liaoxin.service.impl;

import com.liaoxin.domain.Mail;
import liaoxin.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendFrom);
        mailMessage.setTo(mail.getSendTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getText());
        mailMessage.setSentDate(new Date());
        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendHTMLMail(Mail mail) {

    }

    @Override
    public void sendAttachMail(Mail mail) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setFrom(sendFrom);
            messageHelper.setTo(mail.getSendTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());
            for(String key : mail.getAttach().keySet()){
                messageHelper.addAttachment(key, mail.getAttach().get(key));
            }
            messageHelper.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mailMessage);
    }

}
