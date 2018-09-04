package com.zzm.rabbitmq.enums;

import lombok.Getter;

/**
 * @version <p>V1.0</p>
 * @Author: <H2>james</H2>
 * @Description: <P>队列枚举</P>
 * @Date: <P>CREATE IS 2018/9/4 18:07</P>
 **/
@Getter
public enum  QueueEnum {

    /**
     * 队列枚举值
     */
    USER_REGISTER("user.register.queue","user.register");

    /**
     * 队列名称
     */
    private String name;

    /**
     * 队列路由键
     */
    private String routingKey;

    QueueEnum(String name, String routingKey) {
        this.name = name;
        this.routingKey = routingKey;
    }

}
