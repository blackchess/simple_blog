import com.liaoxin.domain.Mail;
import liaoxin.service.MailService;
import liaoxin.service.VerificationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VerificationApplication.class)
public class SendMailTest {

    @Autowired
    MailService mailService;

    @Test
    public void simpleMailTest(){
        Mail mail = new Mail();
        mail.setSendTo("287782773@qq.com");
        mail.setSubject("Blog邮件测试");
        mail.setText("测试邮件");
        mailService.sendSimpleMail(mail);
    }

    @Test
    public void AttachMailTest(){
        Mail mail = new Mail();
        mail.setSendTo("287782773@qq.com");
        mail.setSubject("Blog邮件测试");
        mail.setText("测试邮件");
        Map<String, File> map = new HashMap<>();
        //map.put("测试.jpg",new File("src/main/resources/test.jpg"));
        //map.put("测试.gif",new File("src/main/resources/test2.gif"));
        mail.setAttach(map);
        mailService.sendAttachMail(mail);
    }

}
