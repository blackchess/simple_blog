package com.liaoxin.service.impl;

import com.liaoxin.service.MessageProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;


/**
 * @Auther: liaoxin
 * @Date: 2023/1/11
 **/
@Service
public class MessageProductImpl implements MessageProduct {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RabbitConfirmCallBack rabbitConfirmCallBack;

    @PostConstruct
    private void init(){
        rabbitTemplate.setConfirmCallback(rabbitConfirmCallBack);
    }

    @Override
    public void sendMessage(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("TestDirectExchange","TestRouting",message,correlationData);

    }
}
