package com.liaoxin.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @Auther: liaoxin
 * @Date: 2023/1/10
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("TestQueue",true);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("TestDirectExchange",true,true);
    }

    @Bean
    public Binding bind(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("TestRouting");
    }

}
