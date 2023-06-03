package liaoxin.service;


import liaoxin.model.MailEntity;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/20
 **/
public interface MailService {

    /**
     * 简单邮件功能
     */
    void sendSimpleMail(MailEntity mailEntity);

    /**
     * HTML邮件功能
     */
    void sendHTMLMail(MailEntity mailEntity);

    /**
     * 附件邮件功能
     */
    void sendAttachMail(MailEntity mailEntity);
}
