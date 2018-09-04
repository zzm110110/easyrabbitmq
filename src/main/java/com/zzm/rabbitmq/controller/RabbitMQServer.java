package com.zzm.rabbitmq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

/**
 * @Author: james
 * @Description: rabbitmq服务端
 * @Date: created in 13:57 2018/8/17
 * @Modified By:消息接收类
 **/
@Controller
public class RabbitMQServer {

    /**
     * 消费者
     * @param object
     */
    @RabbitListener(queues = "user.register.queue")
    public void receive(String object) {
        System.out.println("[接收消息][" + object+"]");
    }

}