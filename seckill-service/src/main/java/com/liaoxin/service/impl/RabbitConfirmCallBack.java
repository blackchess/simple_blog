package com.liaoxin.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/12
 **/
@Slf4j
@Component
public class RabbitConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //消息发布确认（消息发布到交换机后回调该方法）
//            if (ack) {
//                log.info("交换机发回消息确认：id:{},message:{}", correlationData.getId(), correlationData.getReturned().getMessage());
//            } else {
//                log.warn("交换机发回未接收：id:{},message:{}", correlationData.getId(), correlationData.getReturned().getMessage());
//            }
            System.out.println("交换机发回消息确认：id:{},message:{}");
    }
}
