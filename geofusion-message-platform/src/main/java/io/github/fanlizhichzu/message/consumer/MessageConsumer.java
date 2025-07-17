package io.github.fanlizhichzu.message.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    // 监听队列消息
    @JmsListener(destination = "sample.queue")
    public void receiveQueueMessage(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }

    // 监听主题消息
    @JmsListener(destination = "sample.topic", containerFactory = "jmsListenerContainerFactory")
    public void receiveTopicMessage(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }
}
