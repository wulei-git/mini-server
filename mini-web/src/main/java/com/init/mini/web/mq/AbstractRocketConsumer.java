package com.init.mini.web.mq;

import org.apache.rocketmq.client.consumer.MQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;

public abstract class AbstractRocketConsumer implements RocketConsumer {

    protected String topics;
    protected String tags;
    protected MessageListener messageListener;
    protected String consumerTitel;
    protected MQPushConsumer mqPushConsumer;

    /**
     * 必要的信息
     *
     * @param topics
     * @param tags
     * @param consumerTitel
     */
    public void necessary(String topics, String tags, String consumerTitel) {
        this.topics = topics;
        this.tags = tags;
        this.consumerTitel = consumerTitel;
    }

    @Override
    public abstract void init();

    @Override
    public void registerMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
