package liaoxin.service;


import liaoxin.model.SmsEntity;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/19
 **/
public interface MessageService {

    /**
     * 发送手机短信
     * @param smsEntity 短信消息模板
     */
    void sendMessage(SmsEntity smsEntity);

}
