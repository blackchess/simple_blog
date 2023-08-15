package liaoxin.sender;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.common.exception.AppException;
import liaoxin.conf.AliSmsConf;
import liaoxin.model.SmsEntity;
import liaoxin.service.SenderService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消息发送服务类
 */
@Service
public class RabbitMQSenderImpl implements SenderService {

    @Resource
    Client client;
    @Resource
    AliSmsConf aliSmsConf;

    @Override
    public void sendMessage(SmsEntity smsEntity) {
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setTemplateCode(aliSmsConf.getTemplateCode());
        sendSmsRequest.setSignName(aliSmsConf.getSignName());
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
    @RabbitListener(queues = "messageQueue")
    public void fetchMessage(String message) {
        SmsEntity smsEntity = JSONObject.parseObject(message,SmsEntity.class);
        if(smsEntity == null){
            throw new AppException("获取信息内容错误");
        }
        sendMessage(smsEntity);
    }


    @Bean
    public Queue messageQueue(){
        return new Queue("messageQueue", true, false, false);
    }

    @Bean
    public Exchange messageExchange(){
        return new DirectExchange("messageExchage", true, false);
    }

    @Bean
    public Binding messageBinding(){
        return new Binding("messageQueue", Binding.DestinationType.QUEUE,"messageExchange","messageRout",null);
    }
}
