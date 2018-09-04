package com.zzm.rabbitmq.service;

import com.zzm.rabbitmq.enums.ExchangeEnum;
import com.zzm.rabbitmq.enums.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @version <p>V1.0</p>
 * @Author: <H2>james</H2>
 * @Description: <P>添加说明</P>
 * @Date: <P>CREATE IS 2018/9/4 18:32</P>
 **/
public interface QueueMessageService extends RabbitTemplate.ConfirmCallback {
    /**
     * 发送消息到rabbitmq消息队列
     * @param message 消息内容
     * @param exchangeEnum 交换配置枚举
     * @param queueEnum 队列配置枚举
     * @throws Exception
     */
    void send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception;

}
