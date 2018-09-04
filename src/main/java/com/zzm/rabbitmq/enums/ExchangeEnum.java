package com.zzm.rabbitmq.enums;

import lombok.Getter;

/**
 * @version <p>V1.0</p>
 * @Author: <H2>james</H2>
 * @Description: <P>rabbitmq交换配置枚举</P>
 * @Date: <P>CREATE IS 2018/9/4 18:11</P>
 **/
@Getter
public enum ExchangeEnum {
    /**
     * 用户注册交换配置枚举
     */
    USER_REGISTER("user.register.topic.exchange");

    private String value;

    ExchangeEnum(String value) {
        this.value = value;
    }
}
