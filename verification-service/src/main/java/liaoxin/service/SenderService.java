package liaoxin.service;

import liaoxin.model.SmsEntity;

public interface SenderService {

    void sendMessage(SmsEntity smsEntity);

}
