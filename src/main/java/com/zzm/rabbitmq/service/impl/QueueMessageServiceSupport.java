package com.zzm.rabbitmq.service.impl;

import com.zzm.rabbitmq.enums.ExchangeEnum;
import com.zzm.rabbitmq.enums.QueueEnum;
import com.zzm.rabbitmq.service.QueueMessageService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @version <p>V1.0</p>
 * @Author: <H2>james</H2>
 * @Description: <P>添加说明</P>
 * @Date: <P>CREATE IS 2018/9/4 18:33</P>
 **/
@Service
public class QueueMessageServiceSupport implements QueueMessageService {

    /**
     * 消息队列模板
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 配置路由交换对象实例
     * @return
     */
    @Bean
    public DirectExchange userRegisterDirectExchange()
    {
        return new DirectExchange(ExchangeEnum.USER_REGISTER.getValue());
    }

    /**
     * 配置用户注册队列对象实例
     * 并设置持久化队列
     * @return
     */
    @Bean
    public Queue userRegisterQueue()
    {
        return new Queue(QueueEnum.USER_REGISTER.getName(),true);
    }

    /**
     * 将用户注册队列绑定到路由交换配置上并设置指定路由键进行转发
     * @return
     */
    @Bean
    public Binding userRegisterBinding()
    {
        return BindingBuilder.bind(userRegisterQueue()).to(userRegisterDirectExchange()).with(QueueEnum.USER_REGISTER.getRoutingKey());
    }

    /**
     * 生产者
     * @param message 消息内容
     * @param exchangeEnum 交换配置枚举
     * @param queueEnum 队列配置枚举
     * @throws Exception
     */
    @Override
    public void send(Object message, ExchangeEnum exchangeEnum, QueueEnum queueEnum) throws Exception {
        //设置回调为当前类对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调id为uuid
        CorrelationData correlationId = new CorrelationData(message.toString());
        //发送消息到消息队列
        rabbitTemplate.convertAndSend(exchangeEnum.getValue(),queueEnum.getRoutingKey(),message,correlationId);
    }

    /**
     * 消息发送成功后，将会回调该类
     * @param correlationData
     * @param b 判断消息有没有发送成功
     * @param s 消息失败的回调信息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("[消息发送成功][将来回调后的ID]["+correlationData.getId()+"]");
        } else {
            System.out.println("[消息发送失败][将来回调后的ID]["+correlationData.getId()+"]" + s);
        }
    }
}
