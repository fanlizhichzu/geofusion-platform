package io.github.fanlizhichzu.message.producer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // 发送消息到队列
    public void sendToQueue(String queueName, Object message) {
        jmsTemplate.convertAndSend(queueName, message);
    }

    // 发送消息到主题
    public void sendToTopic(String topicName, Object message) {
        jmsTemplate.setPubSubDomain(true); // 设置为发布/订阅模式
        jmsTemplate.convertAndSend(topicName, message);
        jmsTemplate.setPubSubDomain(false); // 恢复为点对点模式
    }
}
