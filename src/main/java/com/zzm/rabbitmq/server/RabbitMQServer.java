package com.zzm.rabbitmq.server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: james
 * @Description: rabbitmq服务端
 * @Date: created in 13:57 2018/8/17
 * @Modified By:消息接收类
 **/
@Component
public class RabbitMQServer {
    @RabbitListener(queues = "james")
    public void receive(String object) {
        System.out.println("MESSAGES\t" + object);
    }
}
