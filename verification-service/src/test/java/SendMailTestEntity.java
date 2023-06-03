
import liaoxin.model.MailEntity;
import liaoxin.service.MailService;
import liaoxin.VerificationApplication;
import liaoxin.service.MessageService;
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
public class SendMailTestEntity {

    @Autowired
    MailService mailService;
    @Autowired
    MessageService messageService;

    @Test
    public void simpleMailTest(){
        MailEntity mailEntity = new MailEntity();
        mailEntity.setSendTo("287782773@qq.com");
        mailEntity.setSubject("Blog邮件测试");
        mailEntity.setText("测试邮件");
        mailService.sendSimpleMail(mailEntity);
    }

    @Test
    public void AttachMailTest(){
        MailEntity mailEntity = new MailEntity();
        mailEntity.setSendTo("287782773@qq.com");
        mailEntity.setSubject("Blog邮件测试");
        mailEntity.setText("测试邮件");
        Map<String, File> map = new HashMap<>();
        //map.put("测试.jpg",new File("src/main/resources/test.jpg"));
        //map.put("测试.gif",new File("src/main/resources/test2.gif"));
        mailEntity.setAttach(map);
        mailService.sendAttachMail(mailEntity);
    }

}
