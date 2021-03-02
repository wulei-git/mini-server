package com.init.mini.web.mq;

import org.apache.rocketmq.client.consumer.listener.MessageListener;

/**
 * 消费者接口
 */
public interface RocketConsumer {
    /**
     * 初始化消费者
     */
    void init();

    /**
     * 注册监听
     * @param messageListener
     */
    void registerMessageListener(MessageListener messageListener);
}
