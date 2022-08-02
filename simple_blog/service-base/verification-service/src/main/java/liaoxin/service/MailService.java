package liaoxin.service;

import com.liaoxin.domain.Mail;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
public interface MailService {

    /**
     * 简单邮件功能
     */
    void sendSimpleMail(Mail mail);

    /**
     * HTML邮件功能
     */
    void sendHTMLMail(Mail mail);

    /**
     * 附件邮件功能
     */
    void sendAttachMail(Mail mail);
}
