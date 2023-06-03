package liaoxin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import liaoxin.model.SmsEntity;
import liaoxin.service.MessageService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/11
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    Client client;

    @Override
    public void sendMessage(SmsEntity smsEntity) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setTemplateCode("SMS_154950909");
        sendSmsRequest.setSignName("阿里云短信测试");
        sendSmsRequest.setPhoneNumbers(smsEntity.getMobileNum());
        sendSmsRequest.setTemplateParam(smsEntity.getMessage());
        try {
            SendSmsResponse response = client.sendSms(sendSmsRequest);
            if(!WebConst.STATUS_Code_200.equals(response.getStatusCode())){
                throw new AppException("短信发送失败，云短信服务响应错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "phoneQueue")
    public void fetchMessage(String message) {
        SmsEntity smsEntity = JSONObject.parseObject(message,SmsEntity.class);
        if(smsEntity == null){
            throw new AppException("获取信息内容错误");
        }
        sendMessage(smsEntity);
    }
}
