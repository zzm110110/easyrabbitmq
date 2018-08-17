package com.zzm.rabbitmq.client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: james
 * @Description: 这是rabbitmq客户端
 * @Date: created in 13:54 2018/8/17
 * @Modified By:消息发送类
 **/
@Component
public class RabbitMQClient {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String object) {
        rabbitTemplate.convertAndSend("james", object);
    }
}
