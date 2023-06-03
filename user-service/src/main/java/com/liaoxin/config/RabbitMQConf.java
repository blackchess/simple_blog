package com.liaoxin.config;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: liaoxin
 * @Date: 2023/2/15
 **/

@Data
@Configuration
public class RabbitMQConf {

//    @Bean
//    public Queue mailQueue(){
//        return new Queue("mailQueue",true);
//    }
//
//    @Bean
//    public Queue phoneQueue(){
//        return new Queue("phoneQueue",true);
//    }
//
//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange("messageExchange",true,true);
//    }
//
//    @Bean
//    public Binding bindPhone(){
//        return BindingBuilder.bind(phoneQueue()).to(directExchange()).with("phone");
//    }
//
//    @Bean
//    public Binding bindMail(){
//        return BindingBuilder.bind(mailQueue()).to(directExchange()).with("mail");
//    }
}
